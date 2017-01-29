

#include <PololuMaestro.h>
#include <SoftwareSerial.h>
#include <Servo.h>

#define SAMPLE_TIME 100 //millis

#define BATT_PIN A5
#define BT_LED_PIN 4
#define LOW_BATT_PIN 11
#define THRESHOLD 580

Servo front;
Servo side;
/* Arduino micro has only 1 HW serial channel, reserved for PC and debugging */
SoftwareSerial BTserial(8,9); // rx,tx

byte commandInput='Z';
bool bluetoothLed=0; 
int commResult, drive=0, steer=0, lastSteer=1500, lastDrive=1500, voltageLevel;
unsigned long now;

void setup() {
  Serial.begin(9600);
  BTserial.begin(9600);
  BTserial.listen();
  front.attach(6); // Vertical servo signal pin
  side.attach(7); // Horizontal servo signal pin
  pinMode(BT_LED_PIN,INPUT); // In order to detect when the connection LED is blinking (=connection lost)
  pinMode(LOW_BATT_PIN, OUTPUT);// This LED will light up when battery voltage drops below 5.5V
  digitalWrite(LOW_BATT_PIN,LOW); 
  now=millis();
}

void loop() {
  if (millis()-now>=SAMPLE_TIME){
    bluetoothLed=digitalRead(BT_LED_PIN);
    voltageLevel=analogRead(BATT_PIN);    
    if (BTserial.available()>0){            
      commResult=commProtocol(&commandInput);
      if (commResult!=0){
        Serial.println(commResult);        
        while (BTserial.available()>0){
          if  (BTserial.read()=='E') {            
            break; 
          }         
        }                      
      }
      else{
        // What to do if the input packet is invalid            
      }
    }// if serial available    
    else{
      // What to do if there is nothing in the buffer            
    }// else serial available    
  
     /* The following is for when we attach the motors
      *  Angles from 0 to 180 degrees correspond to values
      *  of 1000 (0 degs) to 2000 (180 degs), in Microseconds.
      */ 
    if (bluetoothLed){
      commandInput='w';
    }
    if (voltageLevel<THRESHOLD){
      commandInput='v';
      digitalWrite(LOW_BATT_PIN,HIGH);
    }
    else{
      digitalWrite(LOW_BATT_PIN,LOW);
    }
    switch (commandInput){
      // First batch is for the "weak force" commands
      case 'a':
        drive=1500;
        steer=1500;
        break;
      case 'f':
        drive=1750;
        steer=1500;
        break;
      case 'e':
        drive=1750;
        steer=1250;
        break;
      case 'd':
        drive=1500;
        steer=1250;
        break;
      case 'c':
        drive=1250;
        steer=1250;
        break;
      case 'b':
        drive=1250;
        steer=1500;
        break;
      case 'i':
        drive=1250;
        steer=1750;
        break;
      case 'h':
        drive=1500;
        steer=1750;
        break;
      case 'g':
        drive=1750;
        steer=1750;
        break;
        
      // Second batch is for the "strong force" commands      
      case 'o':
        drive=2000;
        steer=1500;
        break;
      case 'n':
        drive=2000;
        steer=1000;
        break;
      case 'm':
        drive=1500;
        steer=1000;
        break;
      case 'l':
        drive=1000;
        steer=1000;
        break;
      case 'k':
        drive=1000;
        steer=1500;
        break;
      case 'r':
        drive=1000;
        steer=2000;
        break;
      case 'q':
        drive=1500;
        steer=2000;
        break;
      case 'p':
        drive=2000;
        steer=2000;
        break; 
      default:
        drive=1500;
        steer=1500;
        break;       
    } 

    /* The following segment negates servo jittering */
    if (steer!=lastSteer){ 
      side.writeMicroseconds(steer);      
    }
    if (drive!=lastDrive) {
      front.writeMicroseconds(drive);         
    }
        
    // Debug: 
    /*   
    Serial.print("drive: ");
    Serial.print(drive);
    Serial.print(" steer: ");
    Serial.print(steer);
    Serial.print(" command: ");
    Serial.println(char(commandInput));
    */
        
    now=millis();
    lastSteer=steer;
    lastDrive=drive;
  } //if sample time  
      
}//loop
  
int commProtocol (byte *command){ // Acceptable input in the form of: AA<command>E
  int incoming=0;
  delay(1);  
  incoming=BTserial.read();  
  if (incoming=='A'){
    incoming=BTserial.read();
    if (incoming=='A'){
      *command=BTserial.read();            
      if (BTserial.read()!='E') return(40);      
      else{        
        return(0);
      }
    }// second A
    else return(20);
  }// first A
  else return(10);
}


