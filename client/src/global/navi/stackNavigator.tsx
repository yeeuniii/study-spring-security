import React from "react";
import { createStackNavigator } from "@react-navigation/stack";
import DiaryScreen from "../../pages/editdiary/diaryScreen";

// 네비게이터에 등록할 화면들의 타입 정의
export type RootStackParamList = {
  Home: undefined;
  diary: undefined;
  settings: undefined;
};

// Stack 네비게이터 생성
const Stack = createStackNavigator<RootStackParamList>();

const StackNavigator = () => {
  return (
    <>
      <Stack.Navigator initialRouteName="diary">
        <Stack.Screen name="diary" component={DiaryScreen} />
        <Stack.Screen name="settings" component={DiaryScreen} />
      </Stack.Navigator>
    </>
  );
};

export default StackNavigator;
