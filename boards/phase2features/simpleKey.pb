board name=simpleKey gravity=20.0 friction1=0.020 friction2=0.020

  ball name=Ball x=5.0 y=3.0 xVelocity=2.5 yVelocity=2.5

  triangleBumper name=triangle x=5 y=3 orientation=90
    leftFlipper name=FlipL1 x=17 y=15 orientation=90
  
   keyup key=left action=FlipL1
  