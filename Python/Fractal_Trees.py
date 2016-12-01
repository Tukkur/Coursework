import turtle

turdle = turtle.Turtle()
turdle.speed(9)

def tree(t, trunkLength):
    if trunkLength < 3:
        return
    else:
        t.forward(trunkLength)
        t.right(30)
        tree(t, trunkLength-5)
        t.left(60)
        tree(t, trunkLength-5)
        t.right(30)
        t.backward(trunkLength)


tree(turdle,25)
