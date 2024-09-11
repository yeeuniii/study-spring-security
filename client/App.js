import React from 'react';
import {SafeAreaView, StyleSheet} from 'react-native';
import DiaryScreen from './src/pages/editdiary/diaryScreen';

const App = () => {
  return (
    <SafeAreaView style={styles.container}>
      <DiaryScreen />
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1, // 화면을 가득 채움
    justifyContent: 'center', // 세로 중앙 정렬
    alignItems: 'center', // 가로 중앙 정렬
  },
  text: {
    fontSize: 20,
    fontWeight: 'bold',
  },
});

export default App;
