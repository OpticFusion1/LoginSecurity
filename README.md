LoginSecurity
=================
Simple, light, fast and secure user authentication management. Since 2012.  
Now even ligher and faster than before!

# Links
- [SpigotMC](https://www.spigotmc.org/resources/loginsecurity-updated.105385/)

# Features
- 6 useful commands to manage your password
- Light, fast and easy to set up
- Secure password storage using industry-standard cryptography
- Protects and hides user's location and inventory
- IP & time-based session continuation
- Straightforward administrative control
- User-friendly captcha system for new players
- Used by thousands of server owners
- Stay secure with automatic update notifications
- Prevents players from getting kicked for being logged in from another location
- 20+ supported languages and more to come

# Installation
```shell script
git clone https://github.com/OpticFusion1/LoginSecurity.git LoginSecurity
cd LoginSecurity
git submodule init
git submodule update
mvn clean install
```

Update changes in the translations repo using `git submodule update --remote src/main/resources/lang`
