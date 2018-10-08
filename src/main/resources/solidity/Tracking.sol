pragma solidity ^0.4.25;

pragma experimental ABIEncoderV2;

contract Tracking {

    enum TrackingStatus { READY, ACTIVE, INACTIVE, DELIVERED, OUT_OF_STANDARD, TRACKED }
    
	address owner; //agrichain
	address sender; // sender of tracked products
	address receiver; // receiver of tracked products
	TrackingStatus status;
	
	uint maxTemperatureAccepted;
	uint maxTemperatureReceived;
	bool maxTemperatureReached;
	
	uint minTemperatureAccepted;
	uint minTemperatureReceived;
	bool minTemperatureReached;

    constructor() public {
        owner = msg.sender;
        status = TrackingStatus.READY;
    }
    
    /***
     * 
     * 
     * 
     * **/
    function setup(uint _maxTempAccepted, uint _minTemperatureAccepted, address _sender, address _receiver) public onlyOwner payable {
        require(msg.value >= 1000000000000000 wei);
        
        maxTemperatureAccepted = _maxTempAccepted;
        minTemperatureAccepted = _minTemperatureAccepted;
        sender = _sender;
        receiver = _receiver;
        status = TrackingStatus.ACTIVE;
    }
    

    /** This event changes the Track Status, and is called when something very important occurs **/
    event FinishedTrackAndPayed(address _address, uint256 eventValue, TrackingStatus status);
    
    struct Track {
        string idDeviceTrack;
        uint dateTime;
        
        string data;
        string time;
        uint temperature;
        string humidity;
        string latitude;
        string longitute;
    }
    
    /** This event changes the Tracking Status, and is called when something very important occurs **/
    event NewTrack(TrackingStatus status, string _idDeviceTrack, string _date, string _time, uint _temp, string _humidity, string _latitude, string _longitute);
    
    event ChangeStatus(TrackingStatus status);
    
    /** One contract can have zero or n Tracks **/
    Track[] tracks;
    
    /** Max Temperature Tracked */
    Track maxTrack;
    
    /** Min Temperature Tracked */
    Track minTrack;
    
    
    /** */
    modifier onlyOwner() {
        require(msg.sender == owner, "You're not the owner.");
        _;
    }
    
    /** */
    modifier validStatus() {//READY, ACTIVE, INACTIVE, DELIVERED, OUT_OF_STANDARD, TRACKED
        require(status != TrackingStatus.INACTIVE, "Invalid STATUS.");
        require(status != TrackingStatus.DELIVERED, "Invalid STATUS.");
        require(status != TrackingStatus.OUT_OF_STANDARD, "Invalid STATUS.");
        _;
    }
    

    /** */
    function addTrack(string _idDeviceTrack, string _date, string _time, uint _temp, string _humidity, string _latitude, string _longitute) 
    	public onlyOwner validStatus returns (uint256){
    	    
        /**Avoid zero fee commission **/
        notNull(_idDeviceTrack);
        
        /**
         * Verify if this is the max temperature reached in this smart contract
         **/
        bool thisIsMaxTemperatureReached = (_temp >= maxTemperatureAccepted);
        
        /**
         * Verify if this is the minimal temperature reached in this smart contract
         **/
        bool thisIsMinTemperatureReached = (_temp <= minTemperatureAccepted);
        
       
        /**
         * 
         * 
         * 
         **/
        maxTemperatureReceived = (_temp > maxTemperatureReceived ? _temp : maxTemperatureReceived);
        
        /**
         * 
         *
         * 
         * 
         ***/
        minTemperatureReceived = (_temp < minTemperatureReceived ? _temp : minTemperatureReceived);
        
        

        uint256 _id = tracks.push(Track({
            idDeviceTrack:_idDeviceTrack,
            dateTime:now,
			data: _date,
        	time: _time,
        	temperature: _temp,
        	humidity:_humidity,
        	latitude: _latitude,
        	longitute: _longitute
        }));
        
        if(thisIsMaxTemperatureReached && !maxTemperatureReached){
            maxTemperatureReached = thisIsMaxTemperatureReached;
            maxTrack = tracks[_id];

	        log4(
	            "New max value reached",
	            stringToBytes32(maxTrack.idDeviceTrack),
	            stringToBytes32(maxTrack.data),
	            bytes32(msg.sender),
	            bytes32(_id)
	        );
        }
        
        if(thisIsMinTemperatureReached && !minTemperatureReached){
            minTemperatureReached = thisIsMinTemperatureReached;
            minTrack = tracks[_id];
//            
        	log4(
	            "New min value reached",
	            stringToBytes32(maxTrack.idDeviceTrack),
	            stringToBytes32(maxTrack.data),
	            bytes32(msg.sender),
	            bytes32(_id)
	        );
        }
        
        status = TrackingStatus.TRACKED;
        
        emit NewTrack(status,_idDeviceTrack, _date, _time, _temp, _humidity, _latitude, _longitute);
        
        return _id;
        
    }
    
    
    function verifyAndFinalize() public onlyOwner {
        
        address enviarPara;
        
        if(!maxTemperatureReached && !minTemperatureReached){
            //sender.transfer(address(this).balance);
            
            enviarPara = sender;
            status = TrackingStatus.DELIVERED;
        }else{
            //owner.transfer(address(this).balance);
            enviarPara = owner;
            status = TrackingStatus.OUT_OF_STANDARD;
        }
        
        enviarPara.transfer(address(this).balance);
    }
    
    function getMaxTracked() public view onlyOwner returns (string){
        //string[] memory s = new string[](8);
//        s[0] = maxTrack.idDeviceTrack;
//        s[1] = uintToBytes(maxTrack.dateTime);
//        s[2] = maxTrack.data;
//        s[3] = maxTrack.time;
//        s[4] = uintToBytes(maxTrack.temperature);
//        s[5] = maxTrack.humidity;
//        s[6] = maxTrack.latitude;
//        s[7] = maxTrack.longitute;

 		 

        return '1';
    }
    
    function getMinTracked() public view onlyOwner returns (string[]){
        string[] memory s = new string[](8);
        s[0] = minTrack.idDeviceTrack;
        s[1] = uintToBytes(minTrack.dateTime);
        s[2] = minTrack.data;
        s[3] = minTrack.time;
        s[4] = uintToBytes(minTrack.temperature);
        s[5] = minTrack.humidity;
        s[6] = minTrack.latitude;
        s[7] = minTrack.longitute;
        return s;
    }
    
//    function getMinTracked() public view onlyOwner returns (Track){
//        return minTrack;
//    }
    
    /*
     * The function suicide nao eh mais utilizada
     * EIP 6 - Recomends selfdestruct
     */
    function finalizePolicy() public onlyOwner {
        status = TrackingStatus.INACTIVE;
        emit ChangeStatus(status);
        selfdestruct(owner);
    }

	/** */
    function notZero(uint _value) private pure {
        require (_value != 0x0);
    }
    
    function notNull(string _value) private pure {
       bytes memory tempEmptyStringTest = bytes(_value); // Uses memory
       require (tempEmptyStringTest.length > 0x0);
    }
    
    function listTotalTracks() public view returns (uint) {
        return tracks.length;
    }
    
    function getMaxTemperatureReached() public view returns (bool){
        return maxTemperatureReached;
    }
    
    function getMaxTemperatureReceived() public view returns (uint){
        return maxTemperatureReceived;
    }
    
    function stringToBytes32(string memory source) internal pure returns (bytes32 result) {
	    bytes memory tempEmptyStringTest = bytes(source);
	    if (tempEmptyStringTest.length == 0) {
	        return 0x0;
	    }
	
	    assembly {
	        result := mload(add(source, 32))
	    }
	}
	
	function uintToBytes(uint i) internal pure returns (string){
	    if (i == 0) return "0";
	    uint j = i;
	    uint length;
	    while (j != 0){
	        length++;
	        j /= 10;
	    }
	    bytes memory bstr = new bytes(length);
	    uint k = length - 1;
	    while (i != 0){
	        bstr[k--] = byte(48 + i % 10);
	        i /= 10;
	    }
	    return string(bstr);
	}
}
