import {useNavigation} from '@react-navigation/native';
import {StackNavigationProp} from '@react-navigation/stack';
import {RootStackParamList} from './stackNavigator';

type NavigationProp = StackNavigationProp<RootStackParamList>;

const useStackNavigation = () => {
  const navigation = useNavigation<NavigationProp>();

  const navigateToHome = () => {
    navigation.navigate('Home');
  };

  const navigateToDiary = () => {
    navigation.navigate('diary');
  };

  const goBack = () => {
    if (navigation.canGoBack()) {
      navigation.goBack();
    }
  };

  return {
    navigateToHome,
    navigateToDiary,
    goBack,
  };
};

export default useStackNavigation;
