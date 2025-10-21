# Programación Orientada a Objetos

## Documentacion



### Miembros:
- Julio Cesar Salamanca Ortiz
- Julio Cesar Méndez Sandigo
- Nicolás Rafael Laguna Vallejos
- Farid Eduardo Zuñiga Rico
- Roderick Uziel Caldera Torrez

## Cif:
- 24010046
- 24011264
- 23020134
- 23021299
- 23021073

## Profesor:
JOSE DURAN GARCIA


## Documentacion

## Diferencias entre usar HTTPS y SSH

### HTTPS
El protocolo HTTPS, utilizando la URL https://github.com/..., es la forma más sencilla y accesible de conectar con un repositorio de GitHub, especialmente para principiantes o para usuarios que operan en redes con restricciones estrictas. La comunicación entre tu máquina y GitHub se realiza a través del puerto web estándar (443), que rara vez es bloqueado por cortafuegos corporativos o de red. En cuanto a la autenticación, GitHub requiere que uses tu nombre de usuario y un Token de Acceso Personal (PAT), en lugar de tu contraseña. La ventaja principal es la facilidad de configuración, ya que no tienes que generar ni configurar claves especiales. Sin embargo, la desventaja es que, sin un gestor de credenciales, tendrías que ingresar este token con cada operación de Git (push o pull).

### SSH
El protocolo SSH, que usa la URL git@github.com:..., es el método preferido por desarrolladores frecuentes debido a su alta seguridad y comodidad operativa. Se basa en la criptografía de clave pública/privada: tú generas un par de claves, subes la clave pública a tu cuenta de GitHub, y mantienes la clave privada en tu máquina. Una vez configurado, tu cliente Git usa automáticamente esta clave privada para autenticarte sin necesidad de ingresar ninguna credencial (ni contraseña ni token) para la mayoría de las operaciones. Esto garantiza una experiencia rápida y fluida. Desde el punto de vista de la seguridad, una clave SSH robada solo da acceso a tus repositorios, no a la configuración completa de tu cuenta de GitHub, lo que lo hace más seguro que el uso de un PAT con acceso amplio. Su principal inconveniente es que su puerto predeterminado (22) a menudo está bloqueado en algunas redes.

### Llave de SSH
La generación de un par de llaves SSH con el nombre "prueba" se realiza utilizando el comando ssh-keygen -t ed25519 -f ~/.ssh/prueba en la terminal, lo que instruye al sistema para crear dos archivos en el directorio ~/.ssh/ (uno llamado Prueba para la clave privada y otro Prueba.pub para la clave pública) mediante el algoritmo seguro Ed25519; durante este proceso, se solicita al usuario establecer una frase de contraseña (passphrase), que es una capa de seguridad crucial que cifra la clave privada en el disco, asegurando que, aunque el archivo sea comprometido, no se pueda utilizar sin esta contraseña; una vez completado, el usuario debe copiar el contenido del archivo Prueba.pub (la clave pública) y pegarlo en la configuración de claves SSH de su cuenta de GitHub, estableciendo así una identidad segura y exenta de credenciales para todas las futuras interacciones con sus repositorios remotos.


### Push y Commit

## HTTPS:

Ejecutar git remote -v para verificar la configuración del repositorio remoto y confirmar que la URL utiliza el protocolo HTTPS. Si la conexión está configurada con SSH, reemplazarla mediante git remote set-url origin https://github.com/usuario/repositorio.git para habilitar la autenticación basada en credenciales o token personal de acceso. A continuación, preparar los archivos modificados con git add ., registrar los cambios con git commit -m "actualización de documentación técnica" y ejecutar git push origin main (o la rama correspondiente) para enviar los commits al repositorio remoto. Durante el proceso, Git solicitará las credenciales asociadas a la cuenta del servicio remoto o usará el gestor de credenciales del sistema si está configurado. En caso de error de autenticación, verificar la validez del token o las credenciales guardadas. Una vez completado el proceso, confirmar el push inspeccionando el historial de commits o los archivos actualizados directamente en la interfaz del repositorio remoto.




## SSH:

Ejecutar  git remote -v para verificar la configuración del repositorio remoto y confirmar que la URL utiliza el protocolo SSH. Si la conexión aparece configurada con HTTPS, reemplazarla mediante git remote set-url origin git@github.com:usuario/repositorio.git para habilitar la autenticación por clave. A continuación, preparar los archivos modificados con git add . , registrar los cambios con git commit -m "actualización de documentación técnica" y ejecutar git push origin main (o la rama correspondiente) para enviar los commits al repositorio remoto. Git gestionará la autenticación automáticamente a través de la clave SSH configurada en el entorno local, sin requerir credenciales adicionales. En caso de error de autenticación, verificar los permisos de la clave privada y la asociación de la clave pública con la cuenta remota. Una vez completado el proceso, confirmar el push inspeccionando el historial de commits o los archivos actualizados directamente en la interfaz del repositorio remoto.


Git pull


HTTPS:

El protocolo HTTPS utiliza autenticación por credenciales o personal access token (PAT). Para confirmar el tipo de conexión, ejecutar git remote -v y verificar que la URL esté en el formato https://github.com/usuario/repositorio.git. Si no es así, configurarla con git remote set-url origin https://github.com/usuario/repositorio.git. A continuación, situarse en la rama activa con git checkout nombre-de-rama y ejecutar git pull origin main (o la rama que corresponda) para descargar y fusionar los últimos commits del repositorio remoto. Git solicitará el ingreso de usuario y contraseña o token según la configuración del gestor de credenciales del sistema. En caso de conflictos, resolverlos localmente y confirmar los cambios antes de continuar. Verificar la sincronización final mediante git status o git log --oneline para confirmar que el historial local está actualizado respecto al remoto.

SSH:

El protocolo SSH utiliza autenticación mediante clave pública y privada, evitando el uso de contraseñas. Para confirmar que el repositorio usa SSH, ejecutar git remote -v y verificar que la URL tenga el formato git@github.com:usuario/repositorio.git. Si no es así, configurarla con git remote set-url origin git@github.com:usuario/repositorio.git. Una vez validado el origen, posicionarse en la rama correspondiente con git checkout nombre-de-rama y ejecutar git pull origin main (o la rama necesaria) para obtener y fusionar los cambios del repositorio remoto. La autenticación se realiza automáticamente mediante la clave SSH registrada en el sistema. Si se presentan conflictos durante el merge, resolverlos manualmente y confirmar con git add . seguido de git commit. Finalmente, verificar la actualización con git log --oneline --graph --decorate o revisando los archivos locales.

Git Clone
HTTPS:
Cuando necesitas actualizar tu repositorio local utilizando el protocolo HTTPS, el proceso se centra en la autenticación explícita a través de credenciales. Primero, es fundamental verificar que tu repositorio remoto esté configurado correctamente ejecutando git remote -v. Este comando te mostrará las URLs de fetch y push, las cuales deben empezar con https://github.com/.... Si la configuración actual apunta a una dirección SSH, deberás modificarla explícitamente con el comando git remote set-url origin https://github.com/usuario/repositorio.git. Una vez confirmada la configuración, procedes a ejecutar git pull origin main para traer los cambios de la rama principal. Si el repositorio es privado, Git solicitará autenticación. En este punto, deberás proporcionar tu nombre de usuario y un Personal Access Token (PAT), ya que GitHub no acepta contraseñas de cuenta para operaciones de línea de comandos. Para simplificar este flujo, la mayoría de las instalaciones modernas de Git utilizan un gestor de credenciales que almacena de forma segura tu PAT después del primer uso, automatizando los futuros inicios de sesión. Si la operación falla con un error de autenticación, la causa más común es que el token haya expirado, haya sido revocado, o no tenga los permisos necesarios, lo que requeriría generar uno nuevo en la configuración de tu cuenta de GitHub.
SHH:
Utilizar SSH para ejecutar git pull ofrece un flujo de trabajo más fluido y seguro, basado en la autenticación criptográfica sin contraseñas. El primer paso, al igual que con HTTPS, es verificar la configuración del remoto con git remote -v, asegurándose de que la URL siga el formato git@github.com:usuario/repositorio.git. Si el repositorio está configurado para usar HTTPS, deberás cambiarlo al protocolo SSH mediante el comando git remote set-url origin git@github.com:usuario/repositorio.git. Una vez que la URL es correcta, ejecutas el comando git pull origin main. A diferencia de HTTPS, el proceso de autenticación es completamente automático y transparente. Tu sistema utiliza la clave SSH privada guardada en tu máquina local para establecer una conexión segura con GitHub, que a su vez la valida contra la clave pública que previamente registraste en tu cuenta. Este intercambio criptográfico ocurre en segundo plano sin requerir ninguna intervención manual, como introducir un usuario o una contraseña. Si encuentras un error, típicamente un Permission denied (publickey), indica un problema en la configuración de tus claves: ya sea que la clave pública no esté correctamente asociada a tu cuenta de GitHub, que los permisos del archivo de la clave privada en tu computadora sean incorrectos, o que el agente SSH no esté ejecutándose o no tenga la clave cargada.