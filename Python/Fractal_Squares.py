import turtle
bob = turtle.Turtle()

def drawsquare(turtle, length):
    for i in range(0,4):
         turtle.forward(length)
         turtle.right(90)


def repeat(turtle):
    for i in range(0,10):
        drawsquare(bob,100)
        turtle.right(36)


def fractal(turtle, length, iterations):
    drawsquare(turtle,length)
    turtle.pu()
    turtle.goto(length/3,-(length/3))
    turtle.pd()
    drawsquare(turtle,length/3)

    turtle.pu()
    turtle.goto(length/9,-(length/9))
    turtle.pd()
    drawsquare(turtle,length/9)

    turtle.pu()
    turtle.goto(7*(length/9),-(length/9))
    turtle.pd()
    drawsquare(turtle,length/9)



fractal(bob, 200,30)
