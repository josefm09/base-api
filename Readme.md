# ¿Que es JWT? 

JSON Web Token [JWT](https://jwt.io/) es un estándar de código abierto basado en JSON para crear tokens de  acceso que nos permiten securizar las comunicaciones entre cliente y  servidor 

## ¿Cómo funciona? 

1. El cliente se autentica y garantiza su identidad haciendo una  petición al servidor de autenticación. Esta petición puede ser mediante  usuario contraseña, mediante proveedores externos (Google, Facebook,  etc) o mediante otros servicios como LDAP, Active Directory, etc. 
2. Una vez que el servidor de autenticación garantiza la identidad del cliente, se genera un token de acceso (JWT).
3. El cliente usa ese token para acceder a los recursos protegidos que se publican mediante API. 
4. En cada petición, el servidor desencripta el token y comprueba si el cliente tiene permisos para acceder al recurso haciendo una petición al servidor de autorización. 

En este repo se busca llevar a cabo todas las acciones que debe llevar a cabo los distintos servidores en una sola aplicación.

![jwtexplanation](https://blog.softtek.com/hs-fs/hubfs/jwtexplanation.png?width=600&name=jwtexplanation.png)

## Composición del token 

Estos token están compuestos por tres partes: 

- **Header:** contiene el hash que se usa para encriptar el token. 
- **Payload**: contiene una serie de atributos (clave, valor) que se encriptan en el token. 
- **Firma**: contiene header y payload concatenados y encriptados (Header + “.” + Payload + Secret key).