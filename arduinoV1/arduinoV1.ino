#include <SoftwareSerial.h>

SoftwareSerial Bluetooth(0,1); 

char c;
char Menus;
bool sub=false;
bool LED=false;
bool lamp1=false;
bool lamp2=false;
int trigPin = 8;    // Trigger
int echoPin = 7;    // Echo
int duration, cm;

int count=0;
int value;
int RGB=0;
int RGB_final=0;

int R=0;
int G=0;
int B=0;

void setup()  
{  
  Serial.begin(9600);   
  pinMode(2,OUTPUT); 
  pinMode(3,OUTPUT);  
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(9,OUTPUT); 
  pinMode(10,OUTPUT);
  pinMode(11,OUTPUT); 
  
  
 
}  
  
void loop()  
{   

  
     if (Serial.available())  
     {  
      if(LED){
        value = Serial.read()-48;
          if(value==61){
          LED=false;
          c='m';
        }
 
      // Bluetooth.readBytes((char*)&value, sizeof(value));
      
        
      count++;
     if(count==1){
        RGB+=value*1000;
      }
       if(count==2){
        RGB+=value*100;
      }
       if(count==3){
        RGB+=value*10;
      }
       if(count==4){
        RGB+=value;
         
          
        RGB_final=RGB;
        if(RGB_final>=0&&RGB_final<256){
          R=RGB_final;
        }
        if(RGB_final>=1000&&RGB_final<1256){
          G=RGB_final-1000;
        }
        if(RGB_final>=2000&&RGB_final<2256){
          B=RGB_final-2000;
        }
      
        count=0;
        RGB=0;
        
        analogWrite(9,R);
        analogWrite(10,G);
        analogWrite(11,B);
     
      }
    }
 
      else{
       c = Serial.read();
       
       Serial.println(c);
       
      
      if(Menus=='4'&&c=='u'){
  
           UltraSon();
        }
       if(c=='1'&&Menus=='1'){
        
        if(lamp1==false){
          digitalWrite(3,HIGH);
          lamp1=true;
        }
        else{
          digitalWrite(3,LOW);
          lamp1=false;
        }
        
        
       }
       if(c=='2'&&Menus=='1'){
        
        if(lamp2==false){
          digitalWrite(2,HIGH);
          lamp2=true;
        }
        else{
          digitalWrite(2,LOW);
          lamp2=false;
        }
       }
      }
      

      
       if(sub==true){

        Menus=c;
        sub = false;
        
        if(Menus=='0'){
           LED=true;
        }
      }
     
      if(c=='m'){
        sub=true;
        Menus=0;
      }
     

      
        
     
    } 
} 

void UltraSon(){
 
  
 
    digitalWrite(trigPin, LOW);
  delayMicroseconds(5);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
 
  // Read the signal from the sensor: a HIGH pulse whose
  // duration is the time (in microseconds) from the sending
  // of the ping to the reception of its echo off of an object.
  pinMode(echoPin, INPUT);
  duration = pulseIn(echoPin, HIGH);
 
  // Convert the time into a distance
  cm = (duration/2) / 29.1;     // Divide by 29.1 or multiply by 0.0343

 
  Serial.print(cm);
  Serial.print(',');
    
  
  delay(300);
 
}
