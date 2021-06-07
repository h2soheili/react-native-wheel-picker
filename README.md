# React Native Wheel Picker
original work is [@lesliesam](https://github.com/lesliesam/react-native-wheel-picker)
updated to ```cn.aigestudio.wheelpicker:WheelPicker:1.1.3``` for support cyclic feature



## Credits

[@lesliesam](https://github.com/lesliesam) - for the original source code for which this code was forked off of


## Introduction
Cross platform Picker component based on React-native.

Since picker is originally supported by ios while Android only supports a ugly Spinner component. If you want to have the same user behaviour, you can use this.

 
## Getting Started
```
npm install https://github.com/club9822/react-native-wheel-picker
```



## Example code (using functional components)
```
import React, { useState } from 'react';
import { View, Text } from 'react-native';

import Picker from 'club9822/react-native-wheel-picker'
const PickerItem = Picker.Item;

const WheelPicker = () => {
  const [selectedItem, setSelectedItem ] = useState(2);
  const [itemList , setItemList ] = useState(['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5']);

  return (
    <View>
      <Text>
        <Picker style={{width: 150, height: 180}}
          lineColor="#000000" //to set top and bottom line color (Without gradients)
          lineGradientColorFrom="#008000" //to set top and bottom starting gradient line color
          lineGradientColorTo="#FF5733" //to set top and bottom ending gradient
          selectedValue={selectedItem}
          itemStyle={{color:"black", fontSize:26}}
          onValueChange={(index) => setSelectedItem(index) }>
          {itemList.map((value, i) => (
            <PickerItem label={value} value={i} key={i}/>
          ))}
        </Picker>
      </Text>
    </View>
  );
};

export default WheelPicker;
```
## Example code (using classes)
```
import React, { Component } from 'react';
import { Text, View } from 'react-native';

import Picker from 'club9822/react-native-wheel-picker'
const PickerItem = Picker.Item;

export default class WheelPicker extends Component {

  constructor(props) {
    super(props);
    this.state = {
      selectedItem : 2,
      itemList: ['Item 1', 'Item 2', 'Item 3', 'Item 4', 'Item 5']
    };
  }

  onPickerSelect (index) {
    this.setState({
      selectedItem: index,
    });
  }

  onAddItem = () => {
    var name = 'New item';

    if (this.state.itemList.indexOf(name) == -1) {
      this.state.itemList.push(name);
    }

    this.setState({
      selectedItem: this.state.itemList.indexOf(name),
    });
  }

  render () {
    return (
      <View>
        <Picker style={{width: 150, height: 180}}
          lineColor="#000000" //to set top and bottom line color (Without gradients)
          lineGradientColorFrom="#008000" //to set top and bottom starting gradient line color
          lineGradientColorTo="#FF5733" //to set top and bottom ending gradient
          selectedValue={this.state.selectedItem}
          itemStyle={{color:"black", fontSize:26}}
          onValueChange={(index) => this.onPickerSelect(index)}>
            {this.state.itemList.map((value, i) => (
                <PickerItem label={value} value={i} key={i}/>
            ))}
        </Picker>

        <Text style={{margin: 20}}>
          Selected item: {this.state.itemList[this.state.selectedItem]}
        </Text>

        <Text style={{margin: 20}} onPress={this.onAddItem}>
          Add item
        </Text>
      </View>
    );
  }
}
```
