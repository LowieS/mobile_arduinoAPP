

char c;
char Menus;
bool sub=false;
bool LED=false;
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
  
 
}  
  
void loop()  
{   

  
     if (Serial.available())  
     {  
      if(LED){
        
     value = Serial.read()-48;
      
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
        if(RGB_final>=0&&RGB_final<1000){
          R=RGB_final;
        }
        if(RGB_final>=1000&&RGB_final<2000){
          G=RGB_final-1000;
        }
        if(RGB_final>=2000&&RGB_final<3000){
          B=RGB_final-2000;
        }
        count=0;
        RGB=0;
        Serial.println(R);
        Serial.println(G);
        Serial.println(B);
        
      }
    
      }

      else{
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
      }
      

      
       if(sub==true){

        Menus=c;
        sub = false;
      }
     if(c=='0'){
      LED=true;
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
  Serial.print(',');
    
  
  delay(300);
 
}
