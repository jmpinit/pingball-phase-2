board name=Example gravity = 25.0 friction1 = 0.025 friction2 = 0.025

ball name=Ball2 x=18.1 y=1.5 xVelocity=10.0 yVelocity=5.0
ball name=Ball2 x=18.1 y=1.5 xVelocity=10.2 yVelocity=-3.0
ball name=Ball5 x=5.8 y=6.5 xVelocity=-10.0 yVelocity=2.0
ball name=Ball7 x=9.8 y=16.2 xVelocity=-10.0 yVelocity=5.0

# define some bumpers
circleBumper name=Circle x=14 y=13
circleBumper name=Circle2 x=18 y=18
circleBumper name=Circle3 x=17 y=5
squareBumper name=Square1 x=7 y=2
squareBumper name=Square2 x=13 y=5
triangleBumper name=tri1 x=4 y=2 orientation=90
triangleBumper name=tri2 x=5 y=3 orientation=90
triangleBumper name=tri3 x=6 y=4 orientation=90
triangleBumper name=tri4 x=7 y=5 orientation=90
triangleBumper name=tri5 x=8 y=6 orientation=90
triangleBumper name=tri6 x=9 y=7 orientation=90

absorber name=ABS x=10 y=18 width=8 height=2

fire trigger=ABS action=ABS