

char c;
char Menus;
bool sub=false;
int trigPin = 8;    // Trigger
int echoPin = 7;    // Echo
int duration, cm;

int count=0;

void setup()  
{  
  Serial.begin(9600);   
  pinMode(2,OUTPUT); 
  pinMode(3,OUTPUT);  
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  
 
}  
  
void loop()  
{   

  
     if (Serial.available())  
     {  
       c = Serial.read();
      Serial.println(c);
      
      if(Menus=='4'&&c=='u'){
  
           UltraSon();
        }
       if(c=='1'&&Menus=='1'){
        
        
        digitalWrite(3,HIGH);
        digitalWrite(2,LOW);
       }
       if(c=='2'&&Menus=='1'){
        
        
        digitalWrite(2,HIGH);
        digitalWrite(3,LOW);
       }
      

      
       if(sub==true){

        Menus=c;
        sub = false;
      }
     
      if(c=='m'){
        sub=true;
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
  delay(500);
 
}
