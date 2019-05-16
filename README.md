# testWebApp

Eigenlijk bestaat AjpoLog uit de bestanden “Trace.java” en “TraceAspect.aj” in het mapje “src\main\java\aj”. Deze probeert bij method entry en exit de volgende waardes te loggen:
1. Timestamp 
2. Name of the thread on which the call happened 
3. Whether the event refers to a method entry or exit 
4. Identifier (For example: Fully qualified name + object identityHashCode) of the calling object 
5. Identifier of the object that contains the method being called 
6. Method signature and fully qualified name of the method being called 

Stap 1: afhankelijkheden:
a)	Ik gebruik Java 1.8. Je hebt een geïnstalleerde log4j (https://logging.apache.org/log4j/2.x/download.html, binary vorm) en aspectj (http://www.eclipse.org/aspectj/) nodig met toegevoegd aan je CLASSPATH:
<locatie>/log4j-core-2.11.2.jar
<locatie>/log4j-api-2.11.2.jar
<locatie>/aspectj1.9/lib/aspectjrt.jar 

b)	In de src folder voeg ik de map “resources” toe met “log4j2-test.xml” die als ik het goed begrijp aangeeft waar de logs naartoe geschreven moeten worden. In de map “src\main\java” voeg ik de map “aj” toe.

c)	In de POM.xml van de applicatie voeg ik log4j afhankelijkheden toe:
<dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.9.1</version>
        </dependency>
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.9.1</version>
        </dependency>

d)	En AspectJ afhankelijkheden en plug-in:
<dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
            <version>1.8.13</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.13</version>
        </dependency>

<plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>aspectj-maven-plugin</artifactId>
                <version>1.11</version>
                <configuration>
                    <complianceLevel>1.8</complianceLevel>
                    <source>1.8</source>
                    <target>1.8</target>
                    <showWeaveInfo>true</showWeaveInfo>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin> 

Stap 2: bestanden:
Vanuit de command line maak ik een lijst van alle java bestanden die geïnstrumenteerd moeten worden met het script fileLister.java. Dat heb ik geschreven zodat we als we het opschalen niet handmatig alle bestandsnamen hoeven over te nemen:

java fileLister "src"
Output:
src\main\java\aj\Trace.java
src\main\java\com\sample\ChocolateService.java
src\main\java\com\sample\model\ChocolateType.java
src\main\java\com\sample\SelectChocolateServlet.java

Die lijst sla ik op in het bestand files.lst samen met het filepath van TraceAspect.aj. Ik weet eerlijk gezegd niet waarom ook de aspect bestanden in deze lijst moeten staan, misschien omdat de compiler dan weet dat de andere bestanden daarmee worden geïnstrumenteerd?

Stap 3: instrumentatie:
Vervolgens run ik: ajc -1.8 -argfile files.lst. Als ik het goed begrijp gebruik ik dan de AspectJ compiler om de bestanden in files.lst te compileren en weven. Ik controleer of de instrumentatie goed heeft plaatsgevonden door een bestand te de-compileren met jad, bijvoorbeeld:

jad src\main\java\com\sample\ChocolateService.class

Stap 4: applicatie draaien:
Ik run “mvn install” en “mvn tomcat7:run”. Dan klik ik op de knop binnen de applicatie en zie ik de logbestanden verschijnen in logs/ajpologs.log.
