# -*- encoding: utf-8 -*-
import os
from properties import *
activate_this = '/home/kui/work/python/tensorflow/bin/activate_this.py'
#file_path = '../../../../../src/global.properties'
#props = parse(file_path)   #读取文件
#activate_this = props.get('activate_this')  
execfile(activate_this, dict(__file__=activate_this))

classify_image2 = os.path.dirname(__file__)+"/classify_image2.py"
os.system("python "+classify_image2)

