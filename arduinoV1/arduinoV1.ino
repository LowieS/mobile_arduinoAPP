char c;
void setup()  
{  
  Serial.begin(9600);   
  pinMode(2,OUTPUT); 
  pinMode(3,OUTPUT);  
 
}  
  
void loop()  
{  
  
     
     if (Serial.available())  
     {  
       c = Serial.read();
    
       if(c=='t'){
        
        DataT();

        digitalWrite(2,HIGH);
        digitalWrite(3,LOW);
       }

       if(c=='f'){
        
        DataF();
        digitalWrite(3,HIGH);
        digitalWrite(2,LOW);
       }
        
     
    } 
} 

void DataT(){
  Serial.println ("eerste data:");
  Serial.println ("blabbla");
  
}

void DataF(){
  Serial.println ("tweede data:");
  Serial.println ("bloebloe");
}
