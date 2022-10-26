cd ..\java-solutions
set homework_path=info\kgeorgiy\ja\danilin\implementor

javac -cp ..\..\java-advanced-2022\artifacts\info.kgeorgiy.java.advanced.implementor.jar %homework_path%\Implementor.java
jar cfvm Implementor.jar %homework_path%\MANIFEST.MF %homework_path%\*.class