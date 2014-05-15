#This board is testing the parser by ensuring that all types of keys will be parsed correctly.
board name=Ex1 gravity=20.0 friction1=0.020 friction2=0.020

  ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5
  
  
  leftFlipper name=FlipL1 x=4 y=14 orientation=90
  rightFlipper name=FlipR1 x=6 y=11 orientation=0
  
  keyup key=a action=FlipL1
  keyup key=2 action=FlipL1
  keyup key=left action=FlipL1
  keyup key=right action=FlipL1
  keyup key=down action=FlipL1
  keyup key=up action=FlipL1
  keyup key=space action=FlipL1
  keyup key=minus action=FlipL1
  keyup key=equals action=FlipL1
  keyup key=backspace action=FlipL1
    
  
  keydown key=shift action=FlipR1
  keydown key=ctrl action=FlipR1
  keydown key=alt action=FlipR1
  keydown key=meta action=FlipR1
  keydown key=openbracket action=FlipR1
  keydown key=closebracket action=FlipR1  
  keydown key=backslash action=FlipR1
  keydown key=comma action=FlipR1
  keydown key=period action=FlipR1
  keydown key=slash action=FlipR1      
  keydown key=semicolon action=FlipR1      
  keydown key=quote action=FlipR1        
  keydown key=enter action=FlipR1      
  
        

