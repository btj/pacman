javac -cp ~/junit-platform-console-standalone-1.7.0-M1.jar:/Users/bartj/git/logicalcollections/logicalcollections/bin -sourcepath src src/pacman_officialtests/*.java -d bin
jar cf pacman_part3_officialtests.jar -C bin pacman_officialtests
