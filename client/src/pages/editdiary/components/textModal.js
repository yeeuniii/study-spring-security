import React from "react";
import { View, StyleSheet } from "react-native";
import { RichToolbar, actions } from "react-native-pell-rich-editor";
import Icon2 from "react-native-vector-icons/MaterialCommunityIcons";

function TextModal({ richText, textModal, onChangeTextModal }) {
  return (
    <View
      pointerEvents={textModal ? "auto" : "none"}
      style={[styles.richToolbarContainer, { opacity: textModal ? 1 : 0 }]}
    >
      <RichToolbar
        editor={richText}
        actions={[actions.setBold, actions.setItalic, actions.setUnderline]}
        iconMap={{
          [actions.setBold]: () => (
            <Icon2
              name="format-bold"
              size={24}
              color="#000"
              marginHorizontal={23}
            />
          ),
          [actions.setItalic]: () => (
            <Icon2
              name="format-italic"
              size={24}
              color="#000"
              marginHorizontal={23}
            />
          ),
          [actions.setUnderline]: () => (
            <Icon2
              name="format-underline"
              size={24}
              color="#000"
              marginHorizontal={23}
            />
          ),
        }}
        style={styles.richToolbar}
      />
    </View>
  );
}

export default TextModal;

const styles = StyleSheet.create({
  richToolbarContainer: {
    position: "absolute",
    zIndex: 1,
  },
  richToolbar: {
    width: 266,
    height: 38,
    borderWidth: 1,
    backgroundColor: "#FDE299",
    borderRadius: 20,
    marginBottom: 10,
    marginRight: 30,
    opacity: 0.5,
    flexDirection: "row",
    justifyContent: "flex-start",
    alignItems: "center",
  },
  iconContainer: {
    marginHorizontal: 23,
  },
});
