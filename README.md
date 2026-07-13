# Nombre del Proyecto: SpeedG2L
## Integrantes del proyecto: Giménez Ignacio, Laje Eluney, Linzalata Julián Martín.
## Descripción Corta del Juego:
Desarrollaremos un videojuego (en Java 21 LTS,  framework LibGDX en la versión 1.14.2) de carreras plataformero en 2D para PC. Nos basamos en el videojuego “SpeedRunners”, donde dos jugadores en red competirán en un mapa lleno de obstáculos para llegar a la meta en el menor tiempo posible. Se llamará “SPEED G2L”.
## Tecnología Utilizadas:
- Java 21 LTS.
- Framework LibGDX.
- Git y GitHub para el control de versiones.
- Tiled o LDtk para la creación de mapas.


### Plataforma objetivo:
- Escritorio
## Cómo Compilar y Ejecutar


### Requisitos


- Java Development Kit (JDK) 21 o superior.
- Git.
- Un IDE compatible con Gradle, como IntelliJ IDEA o Eclipse.


### Clonar el repositorio


```bash
git clone https://github.com/Julinza2007/SpeedG2L.git
cd SpeedG2L
```


### Ejecutar el proyecto


Desde la terminal:


**Windows**
```bash
gradlew.bat lwjgl3:run
```


**Linux / macOS**
```bash
./gradlew lwjgl3:run
```


También es posible abrir el proyecto desde IntelliJ IDEA o Eclipse como un proyecto Gradle y ejecutar la clase principal del módulo `lwjgl3`.




## Estado Actual del Proyecto:
Configuración inicial y estructura del proyecto.






[//]: # ()
[//]: # (# SpeedG2L)

[//]: # ()
[//]: # (A [libGDX]&#40;https://libgdx.com/&#41; project generated with [gdx-liftoff]&#40;https://github.com/libgdx/gdx-liftoff&#41;.)

[//]: # ()
[//]: # (This project was generated with a template including simple application launchers and an `ApplicationAdapter` extension that draws libGDX logo.)

[//]: # ()
[//]: # (## Platforms)

[//]: # ()
[//]: # (- `core`: Main module with the application logic shared by all platforms.)

[//]: # (- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.)

[//]: # ()
[//]: # (## Gradle)

[//]: # ()
[//]: # (This project uses [Gradle]&#40;https://gradle.org/&#41; to manage dependencies.)

[//]: # (The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.)

[//]: # (Useful Gradle tasks and flags:)

[//]: # ()
[//]: # (- `--continue`: when using this flag, errors will not stop the tasks from running.)

[//]: # (- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.)

[//]: # (- `--offline`: when using this flag, cached dependency archives will be used.)

[//]: # (- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.)

[//]: # (- `build`: builds sources and archives of every project.)

[//]: # (- `cleanEclipse`: removes Eclipse project data.)

[//]: # (- `cleanIdea`: removes IntelliJ project data.)

[//]: # (- `clean`: removes `build` folders, which store compiled classes and built archives.)

[//]: # (- `eclipse`: generates Eclipse project data.)

[//]: # (- `idea`: generates IntelliJ project data.)

[//]: # (- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.)

[//]: # (- `lwjgl3:run`: starts the application.)

[//]: # (- `test`: runs unit tests &#40;if any&#41;.)

[//]: # ()
[//]: # (Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.)

[//]: # (For example, `core:clean` removes `build` folder only from the `core` project.)
