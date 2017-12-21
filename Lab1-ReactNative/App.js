/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */
import React from 'react';
import {StackNavigator} from 'react-navigation';
import Main from "./components/Main";
import Invitation from "./components/Invitation";
import List from "./components/List";
import ListDetail from "./components/ListDetail";

global.taskList = [{
    id: 1, title: "Client1", status: "raresabr@gmail.com", deadline: "20.04.1996",
    description: "Tall, blond"
},
    {
        id: 2, title: "Client2", status: "client@gmail.com", deadline: "21.04.1996",
        description: "Short..."
    },];
global.count = 2;

const App = StackNavigator({
    Main: {
        screen: Main,
    },
    Invitation: {
        screen: Invitation,
    },
    List: {
        screen: List,
    },
    ListDetail: {
        screen: ListDetail,
    }
});


export default App;