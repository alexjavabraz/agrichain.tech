pragma solidity ^0.4.25;

contract Tracking {

    enum TrackingStatus { READY, ACTIVE, INACTIVE, DELIVERED, OUT_OF_STANDARD, TRACKED }
    
	address owner; //agrichain
	address sender; // sender of tracked products
	address receiver; // receiver of tracked products
	TrackingStatus status;
	
	uint maxTemperatureAccepted;
	uint maxTemperatureReceived;
	bool maxTemperatureReached;

    constructor() public {
        owner = msg.sender;
        status = TrackingStatus.READY;
        
    }
    
    /***
     * 
     * 
     * 
     * **/
    function setup(uint _maxTempAccepted, address _sender, address _receiver) public onlyOwner payable {
        require(msg.value == 1 ether);
        maxTemperatureAccepted = _maxTempAccepted;
        sender = _sender;
        receiver = _receiver;
        status = TrackingStatus.ACTIVE;
    }
    

    /** This event changes the Track Status, and is called when something very important occurs **/
    event FinishedTrackAndPayed(address _address, uint256 eventValue, TrackingStatus status);
    
    struct Track {
        uint256 idDeviceTrack;
        uint dateTime;
        
        string data;
        string time;
        uint temperature;
        string humidity;
        string latitude;
        string longitute;
    }
    
    /** This event changes the Tracking Status, and is called when something very important occurs **/
    event NewTrack(TrackingStatus status, uint _idDeviceTrack, string _date, string _time, uint _temp, string _humidity, string _latitude, string _longitute);
    
    event ChangeStatus(TrackingStatus status);
    
    /** One contract can have zero or n Tracks **/
    Track[] tracks;
    
    
    /** */
    modifier onlyOwner() {
        require(msg.sender == owner, "You're not the owner.");
        _;
    }
    

    /** */
    function addTrack(uint _idDeviceTrack, string _date, string _time, uint _temp, string _humidity, string _latitude, string _longitute) 
    	public onlyOwner returns (uint256){
    	    
        /**Avoid zero fee commission **/
        notZero(_idDeviceTrack);
        
        /**
         * 
         **/
        maxTemperatureReached = (_temp >= maxTemperatureAccepted);
        
        /**
         * 
         * 
         * 
         **/
        maxTemperatureReceived = (_temp > maxTemperatureReceived ? _temp : maxTemperatureReceived);
        

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
        
        status = TrackingStatus.TRACKED;
        
        emit NewTrack(status,_idDeviceTrack, _date, _time, _temp, _humidity, _latitude, _longitute);
        
        return _id;
        
    }
    
    function verifyAndFinalize() public onlyOwner payable {
        if(!maxTemperatureReached){
            sender.transfer(msg.value);
            status = TrackingStatus.DELIVERED;
        }else{
            receiver.transfer(msg.value);
            status = TrackingStatus.OUT_OF_STANDARD;
        }
    }
    
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
    
    function listTotalTracks() public view returns (uint) {
        return tracks.length;
    }
}
