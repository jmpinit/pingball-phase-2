board name= simplePortal

  # define a ball
  ball name=Ball x=15.0 y=1.5 xVelocity=0.0 yVelocity=0.5
 
  # add some flippers
  leftFlipper name=FlipL x=4 y=14 orientation=90
  rightFlipper name=FlipR x=6 y=11 orientation=0

  # define an absorber to catch the ball at the bottom
  absorber name=Abs x=0 y=19 width=20 height=1 

  # make the absorber self-triggering
  fire trigger=Abs action=Abs 
  
  #define some portals -> ball should fall into portal Alpha immediately
  portal name=Alpha x=15 y=16 otherPortal=Beta
  portal name=Beta  x=6  y=4  otherPortal=Alpha
  