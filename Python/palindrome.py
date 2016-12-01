#############1111111111111
def ltrcount(string):
    char = 0
    for i in range(0,len(string)):
        if string[i].isalpha():
            char+=1
    return char

#############2222222222222222
def reverse(string):
    r = ""
    x = len(string)-1
    for i in range(0,len(string)):
        r+=string[x-i]
    return r

#############333333333333333
def ispalindrome(string):
    r = reverse(string).lower()
    s = string.lower()
    print(r)
    print(s)
    if s == r:
        return True
    else:
        return False
