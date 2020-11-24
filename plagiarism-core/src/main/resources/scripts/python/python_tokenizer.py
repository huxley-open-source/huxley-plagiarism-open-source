from tokenize import tokenize, NUMBER, STRING, NAME, OP, COMMENT, ENDMARKER, ENCODING
from io import BytesIO
from sys import argv
from base64 import b64decode

def tokenizeSource(source):
    result = []
    jump = 0
    g = tokenize(BytesIO(source.encode('utf-8')).readline)
    for toknum, tokval, _, _, _  in g:
        if jump > 0:
            jump = jump - 1
        elif tokval == 'print':
            jump += 3;
        elif toknum != COMMENT and toknum != ENDMARKER and toknum != ENCODING:
            result.append((toknum, tokval))
            
    return result

base64code = argv[1]
code = b64decode(base64code).decode('UTF-8')
tokens = tokenizeSource(code)

print(tokens)