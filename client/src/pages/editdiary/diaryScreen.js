import React, { useState } from "react";
import {
  View,
  StyleSheet,
  Pressable,
  ImageBackground,
  Image,
} from "react-native";
import TopButton from "./components/topButton";
import Icon from "react-native-vector-icons/Feather";
import OutputModal from "./components/outputModal";
import ImageModal from "./components/imageModal";

function DiaryScreen() {
  const [modalVisible, setModalVisible] = useState(false);
  const [inputText, setInputText] = useState("");
  const [imageModalVisible, setImageModalVisible] = useState(false);
  const [selectImage, setSelectImage] = useState(null);

  function onChangeImageModalVisible() {
    setImageModalVisible(!imageModalVisible);
  }

  function onChangeModalVisible() {
    setModalVisible(!modalVisible);
  }

  function onchangeTextHandler(text) {
    setInputText(text);
  }

  const sendToServer = async () => {
    try {
      const response = await fetch("http://15.164.64.10:8080/", {
        //백엔드 url 필요
        // 백엔드 API URL
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ userInput: inputText }), // 입력값을 JSON으로 변환하여 전송
      });

      const result = await response.json(); // 백엔드로부터 응답 받기
      console.log(result);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  return (
    <ImageBackground
      source={require("../editdiary/asset/background.png")}
      style={styles.imageBackground}
    >
      <View style={styles.mainContainer}>
        <TopButton />
      </View>
      <View style={styles.otherButtonContainer}>
        {selectImage && (
          <Image
            source={{ uri: selectImage.uri }}
            style={styles.imagePreview}
          />
        )}
        <View styles={styles.centerContainer}>
          <View style={styles.textEditContainer}>
            <Pressable onPress={onChangeImageModalVisible}>
              <Icon name={"image"} size={30} />
            </Pressable>
          </View>
          <Pressable style={styles.outputButton} onPress={onChangeModalVisible}>
            <Icon name={"arrow-up-right"} size={30} />
          </Pressable>
        </View>
      </View>
      <ImageModal
        imageModalVisible={imageModalVisible}
        onChangeImageModalVisible={onChangeImageModalVisible}
        setSelectImage={setSelectImage}
      />
      <OutputModal
        onChangeModalVisible={onChangeModalVisible}
        modalVisible={modalVisible}
        Coment={"일기가 작성되었어요!"}
      />
    </ImageBackground>
  );
}

export default DiaryScreen;

const styles = StyleSheet.create({
  mainContainer: {
    alignItems: "center",
    margin: 32,
  },
  imageBackground: {
    flex: 1,
    resizeMode: "cover",
  },
  otherButtonContainer: {
    flex: 1,
    borderWidth: 1,
    justifyContent: "flex-end",
    marginRight: 22,
    flexDirection: "row",
  },
  textEditContainer: {
    width: 38,
    height: 176,
    borderRadius: 19,
    backgroundColor: "white",
    alignItems: "center",
    justifyContent: "center",
  },
  outputButton: {
    width: 38,
    height: 38,
    borderWidth: 0.5,
    borderRadius: 19,
    alignItems: "center",
    justifyContent: "center",
  },
  modalContainer: {
    width: "100%",
    height: 150,
    borderWidth: 1,
    backgroundColor: "white",
  },
  imageModalOverLay: {
    flex: 1,
    justifyContent: "flex-end",
  },
  imagePreview: {
    width: 200,
    height: 200,
    marginRight: 75,
    marginBottom: 30,
  },
  centerContainer: {
    flex: 1,
    flexDirection: "row",
  },
});
