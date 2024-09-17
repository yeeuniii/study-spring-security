import React, { useState, useRef, useEffect } from "react";
import {
  View,
  StyleSheet,
  Pressable,
  ImageBackground,
  TouchableWithoutFeedback,
} from "react-native";
import TopButton from "./components/topButton";
import Icon from "react-native-vector-icons/Feather";
import { RichEditor } from "react-native-pell-rich-editor";
import OutputModal from "./components/outputModal";
import ImageModal from "./components/imageModal";
import RNFS from "react-native-fs";
import Icon2 from "react-native-vector-icons/MaterialCommunityIcons";
import TextModal from "./components/textModal";

function DiaryScreen() {
  const [modalVisible, setModalVisible] = useState(false);
  const [inputText, setInputText] = useState("");
  const [imageModalVisible, setImageModalVisible] = useState(false);
  const [selectImage, setSelectImage] = useState(null);
  const [textModal, setTextModal] = useState(false);

  const richText = useRef();

  function onChangeImageModalVisible() {
    setImageModalVisible(!imageModalVisible);
  }

  function onChangeTextModal() {
    setTextModal(!textModal);
  }

  function onChangeModalVisible() {
    setModalVisible(!modalVisible);
  }

  function closeTextModal() {
    setTextModal(false);
  }

  useEffect(() => {
    if (selectImage) {
      const insertImageToEditor = async () => {
        try {
          const base64Image = await RNFS.readFile(selectImage.uri, "base64");
          const imageSource = `data:image/jpeg;base64,${base64Image}`;

          richText.current?.insertImage(imageSource);
        } catch (error) {
          console.log("Error converting image to base64:", error);
        }
      };
      insertImageToEditor();
    }
  }, [selectImage]);

  const sendToServer = async () => {
    try {
      const response = await fetch("http://15.164.64.10:8080/", {
        //백엔드 url 필요
        // 백엔드 API URL
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ userInput: inputText }),
      });

      const result = await response.json();
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
      <TouchableWithoutFeedback onPress={closeTextModal}>
        <View style={styles.otherButtonContainer}>
          <TextModal
            richText={richText}
            textModal={textModal}
            onChangeTextModal={onChangeTextModal}
          />
          <RichEditor
            ref={richText}
            style={[styles.richEditor, { color: "black" }]}
            placeholder="여기에 일기를 작성하세요..."
            onChange={(text) => setInputText(text)}
          />
          <View styles={styles.centerContainer}>
            <View style={styles.textEditContainer}>
              <Pressable>
                <Icon2
                  name={"format-title"}
                  size={30}
                  onPress={onChangeTextModal}
                />
              </Pressable>
              <Pressable onPress={onChangeImageModalVisible}>
                <Icon name={"image"} size={30} />
              </Pressable>
              <Pressable>
                <Icon name={"plus-circle"} size={30} />
              </Pressable>
            </View>
            <Pressable
              style={styles.outputButton}
              onPress={onChangeModalVisible}
            >
              <Icon name={"arrow-up-right"} size={30} />
            </Pressable>
          </View>
        </View>
      </TouchableWithoutFeedback>
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
  editorContainer: {
    flex: 1,
    marginHorizontal: 16,
    marginTop: 20,
    borderColor: "#ddd",
    borderRadius: 10,
    backgroundColor: "white",
  },
  richEditor: {
    marginTop: 10,
    flex: 1,
    minHeight: 500,
    marginRight: 10,
    marginLeft: 20,
    borderColor: "#ccc",
  },
  otherButtonContainer: {
    flex: 1,
    justifyContent: "flex-end",
    marginRight: 22,
    flexDirection: "row",
  },
  textEditContainer: {
    width: 38,
    height: 176,
    borderRadius: 19,
    backgroundColor: "white",
    marginRight: 30,
    borderWidth: 1,
    alignItems: "center",
    justifyContent: "space-between",
    padding: 3,
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
    marginRight: 20,
    flexDirection: "row",
  },
});
