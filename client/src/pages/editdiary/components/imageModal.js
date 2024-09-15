import React from "react";
import { View, Text, StyleSheet, Modal, Pressable } from "react-native";
import Icon from "react-native-vector-icons/Feather";
import * as Animatable from "react-native-animatable";

function ImageModal({ imageModalVisible, onChangeImageModalVisible }) {
  if (imageModalVisible === undefined) imageModalVisible = false;
  return (
    <Modal
      animationType="fade"
      transparent={true}
      visible={imageModalVisible}
      onRequestClose={onChangeImageModalVisible}
    >
      <View style={styles.imageModalOverLay}>
        <View style={styles.modalContainer}>
          <Text style={styles.textStyle}>입력할 방법을 선택해주세요.</Text>
          <View style={styles.separator}></View>
          <View style={styles.centerContainer}>
            <Pressable
              style={styles.buttonStyle}
              onPress={onChangeImageModalVisible}
            >
              <Icon name="camera" size={50} />
              <Text>카메라</Text>
            </Pressable>
            <Pressable style={styles.buttonStyle}>
              <Icon name="film" size={50} />
              <Text>갤러리</Text>
            </Pressable>
          </View>
        </View>
      </View>
    </Modal>
  );
}

export default ImageModal;

const styles = StyleSheet.create({
  modalContainer: {
    width: "100%",
    height: "22%",
    // borderWidth: 1,
    backgroundColor: "white",
  },
  imageModalOverLay: {
    flex: 1,
    justifyContent: "flex-end",
  },
  textStyle: {
    marginTop: 15,
    marginHorizontal: 20,
    fontSize: 16,
    color: "black",
  },
  separator: {
    marginTop: 5,
    height: 1,
    width: "90%",
    margin: 20,
    backgroundColor: "#000",
  },
  centerContainer: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "space-around",
    // borderWidth: 1,
    // alignItems: "center", // 부모 컨테이너에서 중앙 정렬
  },
  buttonStyle: {
    alignItems: "center",
  },
});
