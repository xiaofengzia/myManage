
import Vue from 'vue'
import Vuex from 'vuex'
/**
 * vux组件
 */
import {
    AjaxPlugin,
    AlertPlugin,
    Cell,
    CellBox,
    CellFormPreview,
    Checker,
    CheckerItem,
    CheckIcon,
    ConfirmPlugin,
    Datetime,
    Divider,
    Flexbox,
    FlexboxItem,
    Group,
    LoadingPlugin,
    Picker,
    Popup,
    PopupPicker,
    Search,
    Selector,
    ToastPlugin,
    TransferDom,
    WechatPlugin,
    XButton,
    XDialog,
    XHeader,
    XImg,
    XInput,
    XSwitch,
    XTable,
    Loading,
    Grid,
    GridItem,
    Panel,
    Confirm,
    Checklist,
    XAddress,
    XTextarea,
    Badge,
    Sticky,
    Tab,
    TabItem,
    Calendar,
    InlineXNumber,
    Radio,
    Timeline,
    TimelineItem,
    BusPlugin
} from 'vux'

import router from './router'
import App from './App'
import store from './store'
import { deleteRequest, getRequest, postRequest, postCommonRequest} from './utils/http'
import global_ from './datas/Global' //引用文件
import { system } from './utils/system'
/**
 * 组件全局配置
 */

import { getAllInsuredInfo} from './utils/voluationUtil' 

const querystring = require("querystring");


import {ShareSheet,  Icon as vantIcon, Button as vantButton, Cell as vantCell, CellGroup as vantCellGroup,Uploader as vantUploader ,
    NavBar as vantNavBar, Tab as vantTab, Tabs as vantTabs, DatetimePicker as vantDatetimePicker, Popup as vantPopup, Field as vantField, Picker as vantPicker, Radio as vantRadio,
    RadioGroup as vantRadioGroup, 
    Checkbox as vantCheckbox, CheckboxGroup as vantCheckboxGroup, Area as vantArea, Empty as vantEmpty, Dialog as vantDialog, Collapse as vantCollapse, CollapseItem as vantCollapseItem, 
    Col as vantCol, Row as vantRow,Image as VanImage,Slider as vantSlider, Toast as vantToast
    //  SubmitBar, Divider, Image as VanImage, Uploader, Card, Tag, List, Skeleton, Loading, PullRefresh, Grid, GridItem, Search
 } from 'vant';
Vue.use(vantIcon).use(vantButton).use(vantCell).use(vantCellGroup).use(vantNavBar).use(vantTab).use(vantTabs).use(vantDatetimePicker)
.use(vantPopup).use(vantField).use(vantPicker).use(vantRadioGroup).use(vantRadio)
.use(vantCheckbox).use(vantCheckboxGroup).use(vantArea).use(vantEmpty).use(vantDialog).use(vantCollapse).use(vantCollapseItem)
.use(vantCol).use(vantRow).use(VanImage).use(vantSlider).use(vantToast).use(vantUploader);
vantToast.setDefaultOptions({ duration: 0 });


/**
 * 加载插件
 */
Vue.use(Vuex)
Vue.use(WechatPlugin)
Vue.use(AjaxPlugin)
Vue.use(LoadingPlugin)
Vue.use(ToastPlugin)
Vue.use(AlertPlugin)
Vue.use(ConfirmPlugin)

Vue.directive('transfer-dom', TransferDom)

Vue.component(Cell.name, Cell)
Vue.component(CellBox.name, CellBox)
Vue.component(CellFormPreview.name, CellFormPreview)
Vue.component(Checker.name, Checker)
Vue.component(CheckerItem.name, CheckerItem)
Vue.component(CheckIcon.name, CheckIcon)
Vue.component(Datetime.name, Datetime)
Vue.component(Divider.name, Divider)
Vue.component(Flexbox.name, Flexbox)
Vue.component(FlexboxItem.name, FlexboxItem)
Vue.component(Group.name, Group)
Vue.component(Picker.name, Picker)
Vue.component(Popup.name, Popup)
Vue.component(PopupPicker.name, PopupPicker)
Vue.component(Selector.name, Selector)
Vue.component(XButton.name, XButton)
Vue.component(XDialog.name, XDialog)
Vue.component(Search.name, Search)
Vue.component(XImg.name, XImg)
Vue.component(XInput.name, XInput)
Vue.component(XSwitch.name, XSwitch)
Vue.component(XTable.name, XTable)
Vue.component(XHeader.name, XHeader)
Vue.component(Loading.name, Loading)
Vue.component(Grid.name, Grid)
Vue.component(GridItem.name, GridItem)
Vue.component(Panel.name, Panel)
Vue.component(Confirm.name, Confirm)
Vue.component(Checklist.name, Checklist)
Vue.component(XAddress.name, XAddress)
Vue.component(XTextarea.name, XTextarea)
Vue.component(Badge.name, Badge)
Vue.component(Sticky.name, Sticky)
Vue.component(Tab.name, Tab)
Vue.component(TabItem.name, TabItem)
Vue.component(Calendar.name, Calendar)
Vue.component(InlineXNumber.name, InlineXNumber)
Vue.component(Radio.name, Radio)
Vue.component(Timeline.name, Timeline)
Vue.component(TimelineItem.name, TimelineItem)
Vue.use(BusPlugin)
    /**
     * 公用组件
     */
Vue.prototype.postCommonRequest = postCommonRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.GLOBAL = global_; //挂载到Vue实例上面
Vue.prototype.querystring = querystring;   
Vue.prototype.system = system;
Vue.prototype.getAllInsuredInfo = getAllInsuredInfo;

/**
 *  日志输出开关
 */
Vue.config.productionTip = true
    /**
     *  点击延迟
     */
    // FastClick.attach(document.body)

/**
 *  创建实例
 */
new Vue({
    store,
    router,
    render: h => h(App)
}).$mount('#app-box')