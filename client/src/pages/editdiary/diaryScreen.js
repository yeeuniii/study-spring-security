import React, {useState} from 'react';
import {
  View,
  Text,
  TextInput,
  StyleSheet,
  Image,
  Pressable,
} from 'react-native';

function DiaryScreen() {
  const [inputText, setInputText] = useState('');

  function onchangeTextHandler(text) {
    setInputText(text);
  }

  const sendToServer = async () => {
    try {
      const response = await fetch('https://your-backend-api.com/api/data', {
        //백엔드 url 필요
        // 백엔드 API URL
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({userInput: inputText}), // 입력값을 JSON으로 변환하여 전송
      });

      const result = await response.json(); // 백엔드로부터 응답 받기
      console.log(result);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <View style={styles.mainContainer}>
      <Text>2024년 9월 20일</Text>
      <Image source={require('../../../asset/temp.jpeg')} />
      <TextInput
        style={styles.textInputStyle}
        multiline={true}
        onChangeText={onchangeTextHandler}
      />
      <View style={styles.buttonContainer}>
        <Pressable style={styles.customButtonStyle}>
          <Text style={styles.buttonText}>임시저장</Text>
        </Pressable>
        <Pressable style={styles.customButtonStyle}>
          <Text style={styles.buttonText}>업로드</Text>
        </Pressable>
      </View>
    </View>
  );
}

export default DiaryScreen;

const styles = StyleSheet.create({
  mainContainer: {
    flex: 1,
    justifyContent: 'center', // 세로 중앙 정렬
    alignItems: 'center',
    // backgroundColor: '#ccc111',
  },
  textInputStyle: {
    // flex: 1,
    height: 200,
    width: 300,
    borderRadius: 8,
    backgroundColor: '#cccccc',
    margin: 12,
  },
  customButtonStyle: {
    height: 24,
    width: 86,
    backgroundColor: '#cccccc',
    margin: 12,
    borderRadius: 8,
  },
  buttonText: {
    flex: 1,
    textAlign: 'center',
  },
  buttonContainer: {
    flexDirection: 'row',
  },
});
