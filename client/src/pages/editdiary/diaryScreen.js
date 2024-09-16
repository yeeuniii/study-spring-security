import React, { useState, useRef } from 'react';
import {
  View,
  StyleSheet,
  Pressable,
  ImageBackground,
  Modal,
} from 'react-native';
import TopButton from './components/topButton';
import Icon from 'react-native-vector-icons/Feather';
import {
  RichEditor,
  RichToolbar,
  actions,
} from 'react-native-pell-rich-editor';
import OutputModal from './components/outputModal';

function DiaryScreen() {
  const [modalVisible, setModalVisible] = useState(false);
  const [inputText, setInputText] = useState('');

  const richText = useRef();

  function onChangeModalVisible() {
    setModalVisible(!modalVisible);
  }

  const sendToServer = async () => {
    try {
      const response = await fetch('http://15.164.64.10:8080/', {
        //백엔드 url 필요
        // 백엔드 API URL
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userInput: inputText }), // 입력값을 JSON으로 변환하여 전송
      });

      const result = await response.json(); // 백엔드로부터 응답 받기
      console.log(result);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <ImageBackground
      source={require('../editdiary/asset/background.png')}
      style={styles.imageBackground}
    >
      <View style={styles.mainContainer}>
        <TopButton />
      </View>
      <View style={styles.editorContainer}>
        <RichToolbar
          editor={richText}
          actions={[actions.setBold, actions.setItalic, actions.setUnderline]}
        />
        <RichEditor
          ref={richText}
          style={styles.richEditor}
          placeholder='여기에 일기를 작성하세요...'
          onChange={(text) => setInputText(text)} // 텍스트가 변경될 때 inputText에 저장
        />
      </View>
      <View style={styles.otherButtonContainer}>
        <View style={styles.textEditContainer}></View>
        <Pressable style={styles.outputButton} onPress={onChangeModalVisible}>
          <Icon name={'arrow-up-right'} size={30} />
        </Pressable>
      </View>
      <OutputModal
        onChangeModalVisible={onChangeModalVisible}
        modalVisible={modalVisible}
        Coment={'일기가 작성되었어요!'}
      />
    </ImageBackground>
  );
}

export default DiaryScreen;

const styles = StyleSheet.create({
  mainContainer: {
    // flex: 1,
    alignItems: 'center',
    margin: 32,
  },
  imageBackground: {
    flex: 1,
    resizeMode: 'cover',
  },
  editorContainer: {
    flex: 1,
    marginHorizontal: 16,
    marginTop: 20,
    borderWidth: 1,
    borderColor: '#ddd',
    borderRadius: 10,
    backgroundColor: 'white',
  },
  richEditor: {
    flex: 1,
    minHeight: 200,
    padding: 10,
    borderColor: '#ccc',
    borderWidth: 1,
  },
  otherButtonContainer: {
    // borderWidth: 1,
    alignItems: 'flex-end',
    justifyContent: 'flex-end',
    marginRight: 22,
  },
  textEditContainer: {
    width: 38,
    height: 176,
    borderWidth: 1,
  },
  outputButton: {
    width: 38,
    height: 38,
    borderWidth: 0.5,
    borderRadius: 19,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
