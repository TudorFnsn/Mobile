import React from 'react';
import {
    View,
    TextInput,
    Button,
} from 'react-native';

export default class ListDetail extends React.Component
{
    constructor(props)
    {
        super(props);

        this.state = {
            id: 0,
            title: "",
            status: "",
            deadline: "",
            description: ""
        };

        if (this.props.navigation.state.params.id !== undefined)
        {
            var toEdit = this.props.navigation.state.params;

            this.state.id = toEdit.id;
            this.state.title = toEdit.title;
            this.state.status = toEdit.status;
            this.state.deadline = toEdit.deadline;
            this.state.description = toEdit.description;
        }

    }

    save()
    {
        if (this.state.id === 0)
        {
            var item = {
                id: global.count,
                title: this.state.title,
                status: this.state.status,
                deadline: this.state.deadline,
                description: this.state.description
            };
            global.count = global.count + 1;
            global.taskList.push(item);
        }
        else
        {
            var item = this.state;
            for (var i = 0; i < global.taskList.length; i++)
            {
                if (global.taskList[i].id === item.id)
                {
                    global.taskList[i] = item;
                }
            }
        }
        this.props.navigation.navigate("List");
    }

    render()
    {
        return (
            <View>
                <TextInput onChangeText={(title) => this.setState({title})} value={this.state.title}/>
                <TextInput onChangeText={(status) => this.setState({status})} value={this.state.status}/>
                <TextInput onChangeText={(deadline) => this.setState({deadline})} value={this.state.deadline}/>
                <TextInput onChangeText={(description) => this.setState({description})} value={this.state.description}/>
                <Button title="save" onPress={() => this.save()} color="#00468A"/>
            </View>
        );
    }

}