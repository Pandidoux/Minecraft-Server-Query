# Minecraft Server Query
Get server informations from a Minecraft server.
This program require `enable-query=true` in your server.properties file.

## Installation
<!-- ### Download
- Just download the Jar file in the realease section -->
### Compile with JDK
1)  Clone the Minecraft Server Query repository or download zip of the source code.
2)  Compile the Jar file from `src/Program.java` using the JDK

## Usage
- Start program with java `java -jar Minecraft-Server-Query.jar`
- Get full help: `java -jar Minecraft-Server-Query.jar -help`
- Basic query example :
  * `java -jar Minecraft-Server-Query.jar -IP 127.0.0.1 -PORT 25565 -FORMAT RAW_BASIC`
- Complete query example :
  * `java -jar Minecraft-Server-Query.jar -IP 127.0.0.1 -PORT 25565 -FORMAT JSON_FULL`
- Output result in a json file :
  * `java -jar Minecraft-Server-Query.jar -IP 127.0.0.1 -PORT 25565 -FORMAT JSON_FULL -OUTPUT query_output.json`

## Output
- Json output example :
```json
{"motd":"Minecraft Server","gamemode":"SMP","map":"world","onlinePlayers":0,"maxPlayers":20,"port":25565,"host":"192.168.206.1","gameID":"MINECRAFT","version":"1.16.5","players":[]}
```
- Raw output example :
```yaml
Minecraft Server, SMP, world, 0, 20, 25565, 192.168.206.1
```

### Credits
This program use parts of the [Minecraft Status Query](https://github.com/rmmccann/Minecraft-Status-Query.git) repository by rmmccann