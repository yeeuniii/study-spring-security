import React from "react";
import {
  Modal,
  View,
  Text,
  StyleSheet,
  Pressable,
  ImageBackground,
} from "react-native";

function OutputModal({ onChangeModalVisible, modalVisible, Coment }) {
  if (modalVisible === undefined) modalVisible = false;
  return (
    <Modal
      animationType="fade"
      transparent={true}
      visible={modalVisible}
      onRequestClose={onChangeModalVisible}
    >
      <ImageBackground
        source={require("../asset/background.png")}
        style={styles.modalImageBackground}
      >
        <View style={styles.outputModalContainer}>
          <Text style={styles.outputModalText}>{Coment}</Text>
          <Pressable
            style={styles.outputModalButton}
            onPress={onChangeModalVisible}
          >
            <Text style={styles.buttonText}>확인</Text>
          </Pressable>
        </View>
      </ImageBackground>
    </Modal>
  );
}

export default OutputModal;

const styles = StyleSheet.create({
  modalImageBackground: {
    flex: 1,
    resizeMode: "cover",
    alignItems: "center",
    justifyContent: "center",
  },
  outputModalContainer: {
    width: 290,
    height: 444,
    borderRadius: 150,
    backgroundColor: "rgba(255, 255, 255, 0.5)",
    alignItems: "center",
    justifyContent: "center",
  },
  outputModalText: {
    fontSize: 20,
    fontWeight: "bold",
    marginBottom: 100,
    marginTop: 70,
    color: "black",
  },
  outputModalButton: {
    width: 150,
    height: 50,
    borderWidth: 1,
    backgroundColor: "white",
    borderRadius: 20,
    alignItems: "center",
    justifyContent: "center",
  },
  buttonText: {
    fontSize: 15,
    color: "black",
    fontWeight: "bold",
  },
});
