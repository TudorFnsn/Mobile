import React from 'react';
import {
    StyleSheet,
    View,
    Button,
} from 'react-native';

export default class Main extends React.Component
{
    constructor(props)
    {
        super(props);
    }

    render()
    {
        return (
            <View style={styles.container}>
                <View style={styles.buttonContainer}>
                    <Button
                        onPress={() => this.props.navigation.navigate('Invitation')}
                        title="Input Form"
                        color="#006DAF"
                    />
                </View>

                <View style={styles.buttonContainer}>
                    <Button
                        onPress={() => this.props.navigation.navigate('List')}
                        title="List Elements"
                        color="#03983E"
                    />
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
    },
    buttonContainer: {
        flex: 1,
    }
});
