# KlotinEcommerceMVVM

Aplicación Android de comercio electrónico con dos perfiles: **cliente** (explora el catálogo) y **administrador** (gestiona categorías y productos). Está hecha con Kotlin y Jetpack Compose, siguiendo una arquitectura MVVM por capas.


## Funcionalidades

- **Autenticación:** registro con validación por campo e inicio de sesión. La sesión se conserva en el dispositivo.
- **Roles:** si el usuario tiene más de un rol elige cómo ingresar, y puede cambiar de rol desde el perfil sin cerrar sesión.
- **Cliente:** catálogo general, categorías, productos por categoría y detalle con carrusel de imágenes y selector de cantidad.
- **Administrador:** crear, editar y eliminar categorías y productos, con imágenes.
- **Papelera:** el borrado es lógico. Pide confirmación (y avisa si la categoría tiene productos) y los elementos se restauran desde la pestaña Papelera.
- **Experiencia de usuario:** animación de inicio, esqueletos de carga, estados vacío y de error con reintento, y notificaciones propias con el logo de la app.
- **Funcionamiento sin conexión:** el servidor es la fuente de datos; si no hay conexión se muestra la caché local.

## Arquitectura

MVVM por capas, con inyección de dependencias y un flujo de datos en una sola dirección:

```
Composable (UI) ──► ViewModel ──► UseCase ──► Repository (interfaz)
                                                    │
                                            Repository (impl)
                                          ┌─────────┴──────────┐
                                  RemoteDataSource        LocalDataSource
                                  (Retrofit)              (Room / DataStore)
```

La interfaz observa el estado del ViewModel (`Resource`: `Loading`, `Success`, `Failure`) y reacciona a él. Los ViewModels solo conocen casos de uso, y los casos de uso solo conocen las interfaces de los repositorios.

### Estructura del proyecto

```
app/src/main/java/com/dogo/klotinecommercemvvm/
├── core/            Configuración (URL base del servidor)
├── data/            Capa de datos
│   ├── dataSource/
│   │   ├── remote/  Servicios Retrofit y fuentes de datos remotas
│   │   └── local/   Room (DAOs, entidades, base de datos) y DataStore (sesión)
│   ├── mapper/      Conversión entre entidades de Room y modelos de dominio
│   └── repository/  Implementaciones de los repositorios
├── domain/          Capa de dominio, sin dependencias de Android
│   ├── model/       Modelos (Product, Category, User, Rol...)
│   ├── repository/  Interfaces de los repositorios
│   ├── useCase/     Casos de uso agrupados por módulo
│   └── util/        Resource, manejo de errores HTTP
├── id/              Módulos de Hilt
├── presentation/    Capa de presentación
│   ├── components/  Componentes reutilizables (tarjetas, esqueletos, notificaciones)
│   ├── navigation/  Grafos y rutas de navegación
│   └── screens/     Pantallas por módulo (auth, roles, admin, client, profile)
└── ui/theme/        Tema, colores y tipografía
```

### Decisiones principales

- **Servidor primero, caché de respaldo.** Los listados consultan al servidor y sincronizan Room con su respuesta, incluido el borrado de filas que ya no existen. Si no hay conexión y hay datos guardados, se muestran; si no hay datos, se muestra un error con reintento y no una lista vacía.
- **Borrado lógico.** Eliminar solo marca el elemento en el backend. La caché local se actualiza únicamente cuando el servidor lo confirma.
- **Navegación por grafos.** Hay un grafo raíz con la animación de inicio, un grafo de autenticación y grafos de roles, administrador y cliente.
- **Notificaciones globales.** Un mensajero (`AppMessenger`) se expone con `CompositionLocal` y muestra los avisos sobre cualquier pantalla.

## Tecnologías

| Área | Tecnología |
|---|---|
| Lenguaje | Kotlin 2.1.21 |
| Interfaz | Jetpack Compose (BOM 2025.05.00) y Material 3 |
| Navegación | Navigation Compose 2.9.0 |
| Inyección de dependencias | Hilt 2.56.2 (con KSP) |
| Red | Retrofit 2.9.0, OkHttp y conversor Gson |
| Persistencia local | Room 2.8.4 y DataStore Preferences 1.0.0 |
| Imágenes | Coil 3.4.0 |
| Asincronía | Coroutines y Flow |
| Errores del servidor | Moshi 1.14.0 |
| Pruebas | JUnit 4 |
| Compilación | Android Gradle Plugin 8.13.2, Gradle Kotlin DSL |

SDK mínimo 24, destino y compilación 36, Java 11.

## Cómo ejecutarla

1. Abre el proyecto en Android Studio.
2. Edita `core/Config.kt` y cambia `BASE_URL` por la dirección de tu servidor. En el emulador, la máquina anfitriona es `http://10.0.2.2:<puerto>/`.
3. Ejecuta la app, o desde la terminal:

```bash
./gradlew assembleDebug
./gradlew testDebugUnitTest
```

La app permite tráfico HTTP sin cifrar (`usesCleartextTraffic`) para desarrollo local. Para producción conviene usar HTTPS.

## Contrato con el backend

La app consume una API REST y envía el token de sesión en el encabezado `Authorization`.

| Método | Ruta | Uso |
|---|---|---|
| POST | `auth/login`, `auth/register` | Autenticación |
| GET / POST / PUT / DELETE | `categories`, `categories/{id}` | Categorías |
| GET / POST / PUT / DELETE | `products`, `products/{id}`, `products/category/{id}` | Productos |
| GET | `categories/trash`, `products/trash` | Elementos eliminados (solo administrador) |
| PUT | `categories/{id}/restore`, `products/{id}/restore` | Restaurar (solo administrador) |

Los errores deben responder con `{"statusCode": <código>, "message": "<texto>"}`. Restaurar un producto cuya categoría está eliminada debe responder `409`.

Los endpoints de la papelera (`trash` y `restore`) están definidos en la app, pero requieren que el servidor los implemente. Mientras no existan, la pestaña Papelera muestra un aviso de que el servidor no soporta la operación.
