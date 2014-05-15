# This board tests interacting with the flippers via. the keyboard
board name=Ex1 gravity=20.0 friction1=0.020 friction2=0.020

  # define a ball
  ball name=Ball x=0.5 y=0.5 xVelocity=2.5 yVelocity=2.5

  # define a series of square bumpers
  squareBumper name=Square x=15 y=12
  
  # define a series of circle bumpers
  circleBumper name=CircleA x=14 y=6
  circleBumper name=CircleB x=14 y=8
  
  # define a series of triangle bumpers
  triangleBumper name=triA x=19 y=7 orientation=90
 
  # add some flippers
  leftFlipper name=FlipL1 x=4 y=14 orientation=90
  rightFlipper name=FlipR1 x=6 y=11 orientation=0

  # define an absorber to catch the ball at the bottom
  absorber name=Abs x=0 y=19 width=20 height=1 

  # make the absorber self-triggering
  fire trigger=Abs action=Abs 
  
  #Correspond keys to flippers
  keyup key=left action=FlipL1
  keyup key=right action=FlipR1