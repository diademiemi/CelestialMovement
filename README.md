# Spigot Plugin Template
This is a template for a simple Spigot Minecraft plugin, including permissions, commands, and listeners.  

## How to use
1. Press the green "Use this template" button above.  
2. Clone the repository to your computer.
3. Open the project in your IDE of choice.
Change the following files, use refactoring tools to change the package name and class names if your IDE supports it. This can save many steps:
    - [ ] `settings.gradle`
      - [ ] Change the `rootProject.name` to the name of your plugin.
    - [ ] `build.gradle`
      - [ ] Change the `group` to your own domain name.
        - If you don't have a domain name, GitHub allows use of your GitHub username like this: `io.github.<username>`
      - [ ] Change the `version` to the version of your plugin.
    - [ ] `src/main/java/dev/queercoded/spigottemplate`
      - [ ] Rename/refactor this directory to your domain and the name of your plugin. Make sure this matches the `group` in `build.gradle`.
    - [ ] `src/main/java/dev/queercoded/spigottemplate/SpigotTemplate.java`
      - [ ] Rename/refactor this file to the name of your plugin.
      - [ ] Change the class name and all it's references (line 9, 14, 58, 60) to the name of your plugin, if your IDE doesn't support refactoring.
      - [ ] Change the `package` statement to your own domain name and the name of your plugin.
      - [ ] Change the import statements for `dev.queercoded.spigottemplate` to your own domain name and the name of your plugin.
      - [ ] If you want to register a command, change the `onEnable` method to register your command on line 35.
      - [ ] If you want to register a listener, change the `onEnable` method to register your listener on line 38.
    - [ ] `src/main/java/dev/queercoded/spigottemplate/command/CommandExec.java`
      - [ ] Rename/refactor this file to the name of your command.
      - [ ] Change the class name and all it's references (line 17)
      - [ ] Change the `package` statement to your own domain name and the name of your plugin.
      - You probably won't get much use out of the existing code in this file, but it serves as an example.
    - [ ] `src/main/java/dev/queercoded/spigottemplate/listener/BlockBreakEventListener.java`
      - [ ] Rename/refactor this file to the name of your listener.
      - [ ] Change the class name and all it's references (line 10, 15)
      - [ ] Change the `package` statement to your own domain name and the name of your plugin.
      - If you want to listen to events, you can change the event it listens to by:
        - [ ] Changing the function below `@EventHandler` annotation on line 22 to the event you want to listen to.
          - [ ] This is done by changing argument to the `public void` method to the event you want to listen to.
          - [ ] Rename the method to something more descriptive.
        - We request if you listen to every event to check for a permission node of the player affected
          - You can register a permission in the main class of this file, an example is given in `SpigotTemplate.java` on line 41.
      - You probably won't get much use out of the existing code in this file, but it serves as an example.
    - [ ] `src/resources/plugin.yml`
      - [ ] Change the `name` to the name of your plugin.
      - [ ] Change the `main` to the name of your main class.
        - This is the class that extends `JavaPlugin` and is the entry point of your plugin.
      - [ ] Change the `authors` to your name.
      - [ ] Change the `description` to a description of your plugin.
      - [ ] Change the `version` to the version of your plugin.
      - [ ] Under `commands`, change `spigottemplate` to the name of your command.
        - [ ] Change the `description` to a description of your command.
        - [ ] Change the `usage` to an example of your command.
  - [ ] `LICENSE`, set this to your preferred open source license
      - Not sure what's right for you? Use [https://choosealicense.com/](https://choosealicense.com/)

### Building
1. Run `gradlew build` in the root directory of the project.
2. The built jar will be in `build/libs`.

This template isn't a comprehensive guide to making a plugin, but it can show you the basics of how to make a plugin.

### License
This template is licensed under Creative Commons Zero, this means the code is essentially in the public domain.  
If you don't want your plugin to be licensed under CC0 too, you should remove this file from your repo for clarity.  
