#This board only tests for the absorber and that it is self-triggering.
board name=ExampleB gravity = 10.0

# define a ball
ball name=BallA x=1.8 y=4.5 xVelocity=10.4 yVelocity=10.3 
ball name=BallB x=10.0 y=13.0 xVelocity=-3.4 yVelocity=-2.3 

# define an absorber to catch the ball
 absorber name=Abs x=10 y=17 width=10 height=2 


# make the absorber self-triggering
 fire trigger=Abs action=Abs 