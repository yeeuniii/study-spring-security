import React from "react";
import { View, Pressable, Text, Image, StyleSheet } from "react-native";
import Icon from "react-native-vector-icons/AntDesign";
import Icon2 from "react-native-vector-icons/Feather";
import OutputModal from "./outputModal";

function TopButton() {
  const today = new Date();

  const formattedDate = `${today.getFullYear()}. ${
    today.getMonth() + 1
  }. ${today.getDate()}`;

  return (
    <View style={styles.topButtonContainer}>
      <Pressable style={styles.pressableBorder}>
        <Icon name="left" size={24} />
      </Pressable>
      <View style={styles.dateContainer}>
        <Text style={styles.dateText}>{formattedDate}</Text>
        <Image
          source={require("../../editdiary/asset/funny.png")}
          style={styles.imageStyle}
        ></Image>
      </View>
      <Pressable style={styles.pressableBorder}>
        <Icon2 name="bookmark" size={24} />
      </Pressable>
      <OutputModal />
    </View>
  );
}

export default TopButton;

const styles = StyleSheet.create({
  topButtonContainer: {
    flexDirection: "row",
  },
  pressableBorder: {
    width: 38,
    height: 38,
    marginHorizontal: 20,
    borderWidth: 0.5,
    borderRadius: 19,
    justifyContent: "center",
    alignItems: "center",
  },
  dateText: {
    fontSize: 16,
    marginHorizontal: 10,
  },
  imageStyle: {
    width: 27,
    height: 27,
  },
  dateContainer: {
    flexDirection: "row",
    width: 230,
    height: 38,
    borderWidth: 0.5,
    borderRadius: 64,
    justifyContent: "center",
    alignItems: "center",
  },
});
