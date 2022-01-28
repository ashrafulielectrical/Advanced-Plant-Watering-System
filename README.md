
# Automated Plant Watering System

## Content 

This project was created for a first year engineering course, Object Oriented Programming, using Java IntelliJ IDEA to create a 
project integrated with Arduino Grove Board.

The idea of this project was to start learning and expand what I already from MATLAB. The goal of this project was recreate
the automated plant watering system in Java with a GUI and new features.

## How it works  

Once the system is turned on the moisture sensor would read the wetness of the water in voltage. This voltage reading would be compared to the values in the program to see if the soil is too dry or wet. Upon comparison if the soil has enough moisture the program would not do anything a would continue keep monitoring. However if the soil was too dry the voltage would high and this would cause the pump to turn on and start watering the plant. While this is happening the LED on the Grove board would light up for as long as water is being pumped.

![App Screenshot](https://media.giphy.com/media/w6kq9zq2x0usB4EdO9/giphy.gif)

Components Used in Project: 

```
 Arduino Grove Board
 Arduino MOSFET
 Water Pump & Plastic Tube
 Moisture Sensor
 OLED & Red LED
 Plant 
```

*The following diagram shows how the components are connected in the system*

![App Screenshot](https://i.imgur.com/VAEyiEp.png?1)


## Graphical User Interface (GUI) 

Once the user runs the program a GUI Window. This GUI includes a `Push` button, a `Slider`, and a `Live Graph` of the Moisture Reading. 

* Software Manual Push Button
> When the button is pressed and held down the pump is turned on for however long the button is held 

![App Screenshot](https://media.giphy.com/media/zHNYCXhnN3FoLxS7nN/giphy-downsized-large.gif)


* OLED Screen Control
> The slider ranges from values 0 to 100. When the slider is set at any value the OLED screen will display the same number

![App Screenshot](https://media.giphy.com/media/PAzHuHF2IsIRYWN272/giphy-downsized-large.gif)   

* Live Moisture Graph

> When the program is started the moisture starts reporting the moisture of the soil and the values are plotted appearing in the graph

![App Screenshot](https://i.imgur.com/YBAGc7Y.jpg)

### Setup Image

![App Screenshot](https://i.imgur.com/wnaGpqT.png)
