import serial

ser_handle = serial.Serial(port="COM5", baudrate=115200, bytesize=serial.EIGHTBITS, parity=serial.PARITY_NONE, stopbits=serial.STOPBITS_ONE, timeout=1, write_timeout=1)

#ser_handle.open()

data = [0xAA, 0xC8, 0xDF, 0x07, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x55]
print(data)
data = bytearray(data)

print(ser_handle.write(data))
ser_handle.flush()

ser_handle.close()