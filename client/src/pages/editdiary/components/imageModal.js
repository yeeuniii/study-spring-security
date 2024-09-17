import React from "react";
import {
  View,
  Text,
  StyleSheet,
  Modal,
  Pressable,
  TouchableWithoutFeedback,
} from "react-native";
import Icon from "react-native-vector-icons/Feather";
import * as ImagePicker from "react-native-image-picker";

function ImageModal({
  imageModalVisible,
  onChangeImageModalVisible,
  setSelectImage,
}) {
  if (imageModalVisible === undefined) imageModalVisible = false;

  const handleCloseModal = () => {
    onChangeImageModalVisible(); // 모달을 닫는 콜백 호출
  };

  const openGallery = () => {
    const options = {
      mediaType: "photo",
      includeBase64: false,
    };

    ImagePicker.launchImageLibrary(options, (response) => {
      if (response.didCancel) {
        // console.log("User cancelled image picker");
      } else if (response.errorCode) {
        // console.log("ImagePicker Error: ", response.errorMessage);
      } else {
        setSelectImage(response.assets[0]);
      }
      onChangeImageModalVisible();
    });
  };

  const openCamera = () => {
    const options = {
      mediaType: "photo",
      saveToPhotos: true,
      cameraType: "back",
    };

    ImagePicker.launchCamera(options, (response) => {
      if (response.didCancel) {
        console.log("User cancelled image picker");
      } else if (response.errorCode) {
        console.log("ImagePicker Error: ", response.errorCode);
      } else {
        console.log("Image URI: ", response.assets[0].uri);
        setSelectImage(response.assets[0]);
      }
      onChangeImageModalVisible();
    });
  };

  return (
    <Modal
      animationType="fade"
      transparent={true}
      visible={imageModalVisible}
      onRequestClose={onChangeImageModalVisible}
    >
      <TouchableWithoutFeedback onPress={handleCloseModal}>
        <View style={styles.imageModalOverLay}>
          <View style={styles.modalContainer}>
            <Text style={styles.textStyle}>입력할 방법을 선택해주세요.</Text>
            <View style={styles.separator}></View>
            <View style={styles.centerContainer}>
              <Pressable style={styles.buttonStyle} onPress={openCamera}>
                <Icon name="camera" size={50} />
                <Text>카메라</Text>
              </Pressable>
              <Pressable style={styles.buttonStyle} onPress={openGallery}>
                <Icon name="film" size={50} />
                <Text>갤러리</Text>
              </Pressable>
            </View>
          </View>
        </View>
      </TouchableWithoutFeedback>
    </Modal>
  );
}

export default ImageModal;

const styles = StyleSheet.create({
  modalContainer: {
    width: "100%",
    height: "22%",
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
  },
  buttonStyle: {
    alignItems: "center",
  },
});
