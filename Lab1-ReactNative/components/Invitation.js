import React from 'react';
import {StyleSheet, View, Button, TextInput, Text, Linking} from 'react-native';

export default class Invitation extends React.Component
{
    constructor(props)
    {
        super(props);
        this.state = {
            mail: "rares@gmail.com", subject: "Invitation",
            title: "Invitation"
        };
    }

    handleEmail = () =>
    {
        var mail = this.state.mail;
        var subject = this.state.subject;
        var title = this.state.title;

        //command to open up the email
        Linking.openURL("mailto:" + JSON.stringify(mail)
            + "?subject=" + JSON.stringify(title) + "&body=" + JSON.stringify(subject));
    };

    render()
    {
        return (
            <View style={styles.container}>
                <View style={styles.rowContainer}>
                    <Text> To: </Text>
                    <TextInput
                        style={{height: 40}}
                        onChangeText={(mail) => this.setState({mail})}
                        value={this.state.mail}
                    />
                </View>

                <View style={styles.rowContainer}>
                    <Text>Subject </Text>
                    <TextInput
                        style={{height: 40}}
                        onChangeText={(title) => this.setState({title})}
                        value={this.state.title}
                    />
                </View>

                <View style={styles.rowContainer}>
                    <Text>Body </Text>
                    <TextInput
                        style={{height: 40}}
                        onChangeText={(subject) => this.setState({subject})}
                        value={this.state.subject}
                    />
                </View>

                <Button
                    onPress={this.handleEmail}
                    title="Send to mail?"
                    color="#000000"
                    accessabilityLabel="Purple Email Me Button"
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'column',
        alignItems: 'center',
        justifyContent: 'center',
    },
    buttonContainer: {
        flex: 1,
    },
    rowContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',

    }
});
