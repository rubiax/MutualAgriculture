# -*- encoding: utf-8 -*-
import os
activate_this = '/home/kui/work/python/tensorflow/bin/activate_this.py'

#file_path = '../../../../../src/global.properties'

# with open(file_path, 'r') as fr:
# 	value = str(fr.readline()).strip()
# fr.close()
# activate_this =  value.split('=')[1]

#props = parse(file_path)   #读取文件
#activate_this = props.get('activate_this')  
execfile(activate_this, dict(__file__=activate_this))

classify_image2 = os.path.dirname(__file__)+"/classify_image2.py"
os.system("python "+classify_image2)

