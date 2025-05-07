/*
  CAN Send Example

  This will setup the CAN controller(MCP2515) to send CAN frames.
  Transmitted frames will be printed to the Serial port.
  Transmits a CAN standard frame every 2 seconds.

  MIT License
  https://github.com/codeljo/AA_MCP2515
*/

#include "AA_MCP2515.h"

// TODO: modify CAN_BITRATE, CAN_PIN_CS(Chip Select) pin, and CAN_PIN_INT(Interrupt) pin as required.
const CANBitrate::Config CAN_BITRATE = CANBitrate::Config_16MHz_500kbps;
const uint8_t CAN_PIN_CS = 10;
const int8_t CAN_PIN_INT = 2;

CANConfig config(CAN_BITRATE, CAN_PIN_CS, CAN_PIN_INT);
CANController CAN(config);

void setup() {
  Serial.begin(115200);

  while (CAN.begin(CANController::Mode::Normal) != CANController::OK) {
    Serial.println("CAN begin FAIL - delaying for 1 second");
    delay(1000);
  }
  Serial.println("CAN Logger initialised");
}

void loop() {
  String CanCmd;
  CANFrame TxFrame;
  CANFrame RxFrame;
  uint16_t counter;

  //Wait for command on Serial port
  counter = 0;
  while (!Serial.available()) {

    if (CAN.read(RxFrame) == CANController::IOResult::OK) {
      RxFrame.print("RX");
    }

    if ((counter == 0) || (counter == 50000)) {
      Serial.println("Waiting for command");
      counter = 0;
    }
    counter++;
    delayMicroseconds(100);
  }

  Serial.println("Processing command" + CanCmd);

  //Read out the entire can command until '\n' is pressed
  while (Serial.available()) {
    CanCmd = Serial.readStringUntil('\n');
  }

  if (CanCmd != NULL) {
    // Extract can data from the can command
    extractCanDataFrmCmd(CanCmd, &TxFrame);

    CAN.write(TxFrame);
    TxFrame.print("CAN TX");
  }
}

bool extractCanDataFrmCmd(String CanCmd, CANFrame *TxFrame) {
  String StrId, StrDataByte;
  uint8_t SpaceIdxArr[9];
  uint8_t SpaceIdxArrLen;
  uint8_t Temp[3];
  uint8_t TxData[8];
  int i, TxLen;

  //Note down string index wherever space character occurs
  for (i = 0, SpaceIdxArrLen = 0; i < CanCmd.length(); i++) {
    if (CanCmd[i] == ' ') {
      //Serial.print(i);
      SpaceIdxArr[SpaceIdxArrLen++] = i;
    }
  }
  SpaceIdxArr[SpaceIdxArrLen++] = CanCmd.length();

  //Serial.println("SpaceIdxArrLen = " + String(SpaceIdxArrLen));
  //Serial.println("CanCmd.length = " + String(CanCmd.length()) + "Last element = " + String(SpaceIdxArr[9]));

  //Extract can id
  StrId = CanCmd.substring(0, SpaceIdxArr[0]);
  for (i = 0; i < StrId.length(); i++) {
    Temp[i] = StrId[i];
  }

  TxFrame->setId(strtol(Temp, 0, 16));

  //Extract data bytes
  for (i = 0, TxLen = 0; i < (SpaceIdxArrLen - 1); i++) {
    //Serial.print("i = " + String(i) + " ");
    //Serial.println("SpaceIdxArr[i]=" + String(SpaceIdxArr[i]) + " SpaceIdxArr[i+1]=" + String(SpaceIdxArr[i + 1]));
    StrDataByte = CanCmd.substring(SpaceIdxArr[i], SpaceIdxArr[i + 1]);
    for (int j = 0; j < StrDataByte.length(); j++) {
      Temp[j] = StrDataByte[j];
    }
    //Serial.println("Bytes: " + StrDataByte);
    TxData[TxLen++] = strtol(Temp, 0, 16);
  }

  TxFrame->setData(TxData, TxLen);
}
