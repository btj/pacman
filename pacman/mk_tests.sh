javac -cp ~/junit-platform-console-standalone-1.7.0-M1.jar -sourcepath src src/pacman_officialtests/*.java -d bin
jar cf pacman_part2_officialtests.jar -C bin pacman_officialtests
