char c;
void setup()  
{  
  Serial.begin(9600);            // Start hardware Serial  
 
}  
  
void loop()  
{  
  
     
     if (Serial.available())  
     {  
       c = Serial.read();
    
       if(c=='t'){
        
        DataT();
       }

       if(c=='f'){
        
        DataF();
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
