#include <Arduino.h>
#include <U8x8lib.h>
#define MOSFET DD2 // The MOSFET driver for the water pump on digital I/O 2
#define REDLED 4 // Big red LED on digital I/O 4
#define BUTTON 6 // Push button on digital I/O 6
auto display = U8X8_SSD1306_128X64_NONAME_HW_I2C(U8X8_PIN_NONE);
void setup() {
 Serial.begin(9600);
 display.begin();
 display.setFlipMode(0);
 display.clearDisplay();
 pinMode(MOSFET, OUTPUT); // Sets the D2 pin (MOSFET + Pump) to output
 pinMode(REDLED, OUTPUT); // Sets the D4 pin (LED) to output
 pinMode(BUTTON, INPUT); // Sets the D6 pin (Button) to input
}

void sendMoistureDataa() {
 const auto value = analogRead(A1);
 const byte data[] = {0, 0, highByte(value), lowByte(value)};
 
 Serial.write(data, 4);
 Serial.println();
}

void loop() {
sendMoistureData();
 delay(750);

const auto value1 = analogRead(A1);

if (value1 > 550 && value1 < 700) {
    digitalWrite(REDLED, HIGH);
    digitalWrite(MOSFET, HIGH);
  } else {
    digitalWrite(REDLED, LOW);
    digitalWrite(MOSFET, LOW);
}

 display.setFont(u8x8_font_profont29_2x3_r);
 display.setCursor(0, 0);
 if (!Serial.available()) {
 return;
 }
 const auto receivedData = Serial.read();
 // format the data
 char buf[16];
 sprintf(buf, "%03d", receivedData);
 if(receivedData<=100){
 display.print(buf);
 digitalWrite(MOSFET,LOW);
 }
 else if (receivedData == 500)
 {
 digitalWrite(MOSFET,HIGH);
 }
 else {
 digitalWrite(MOSFET,LOW);
 }

 
}