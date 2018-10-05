package br.com.bjbraz.smartcontract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.5.0.
 */
public class TrackingV1 extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a031916331781556002805460a060020a60ff0219169055610ad190819061004390396000f30060806040526004361061006c5763ffffffff7c0100000000000000000000000000000000000000000000000000000000600035041663455312ad81146100715780634f2cf05714610088578063535975e2146101f657806380151fa3146101fe57806385e2c30c14610213575b600080fd5b34801561007d57600080fd5b50610086610230565b005b34801561009457600080fd5b5060408051602060046024803582810135601f81018590048502860185019096528585526101e495833595369560449491939091019190819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f818a01358b0180359182018390048302840183018552818452989b8a359b909a90999401975091955091820193509150819084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a99988101979196509182019450925082915084018382808284375050604080516020601f89358b018035918201839004830284018301909452808352979a9998810197919650918201945092508291508401838280828437509497506103269650505050505050565b60408051918252519081900360200190f35b6100866107ba565b34801561020a57600080fd5b506101e46108fc565b610086600435600160a060020a0360243581169060443516610903565b600054600160a060020a03163314610280576040805160e560020a62461bcd0281526020600482015260156024820152600080516020610a86833981519152604482015290519081900360640190fd5b6002805474ff000000000000000000000000000000000000000019167402000000000000000000000000000000000000000017908190556040517f9146df6dc3725d43aefb4c714955239b068466fd5c10ae1abf2ec3453316839e9174010000000000000000000000000000000000000000900460ff16908082600581111561030557fe5b60ff16815260200191505060405180910390a1600054600160a060020a0316ff5b600080548190600160a060020a03163314610379576040805160e560020a62461bcd0281526020600482015260156024820152600080516020610a86833981519152604482015290519081900360640190fd5b610382896109de565b6003546005805460ff19169188101591909117905560045486116103a8576004546103aa565b855b60045560408051610100810182528a81524260208083019182529282018b8152606083018b9052608083018a905260a0830189905260c0830188905260e08301879052600680546001810180835560009290925284517ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d3f600890920291820190815593517ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d40820155915180519195610487937ff652222313e28459528d920b65115c16c04f3efc82aaedc97be59f3f377c0d4101929101906109ed565b50606082015180516104a39160038401916020909101906109ed565b506080820151600482015560a082015180516104c99160058401916020909101906109ed565b5060c082015180516104e59160068401916020909101906109ed565b5060e082015180516105019160078401916020909101906109ed565b50506002805492935060059290915074ff00000000000000000000000000000000000000001916740100000000000000000000000000000000000000008302179055507f602db9c9683a1301d898834f80168e5ebe72d4c93714a51af01d776640511663600260149054906101000a900460ff168a8a8a8a8a8a8a6040518089600581111561058c57fe5b60ff168152602001888152602001806020018060200187815260200180602001806020018060200186810386528c818151815260200191508051906020019080838360005b838110156105e95781810151838201526020016105d1565b50505050905090810190601f1680156106165780820380516001836020036101000a031916815260200191505b5086810385528b5181528b516020918201918d019080838360005b83811015610649578181015183820152602001610631565b50505050905090810190601f1680156106765780820380516001836020036101000a031916815260200191505b5086810384528951815289516020918201918b019080838360005b838110156106a9578181015183820152602001610691565b50505050905090810190601f1680156106d65780820380516001836020036101000a031916815260200191505b5086810383528851815288516020918201918a019080838360005b838110156107095781810151838201526020016106f1565b50505050905090810190601f1680156107365780820380516001836020036101000a031916815260200191505b50868103825287518152875160209182019189019080838360005b83811015610769578181015183820152602001610751565b50505050905090810190601f1680156107965780820380516001836020036101000a031916815260200191505b509d505050505050505050505050505060405180910390a198975050505050505050565b600054600160a060020a0316331461080a576040805160e560020a62461bcd0281526020600482015260156024820152600080516020610a86833981519152604482015290519081900360640190fd5b60055460ff16151561088a57600154604051600160a060020a03909116903480156108fc02916000818181858888f1935050505015801561084f573d6000803e3d6000fd5b506002805474ff00000000000000000000000000000000000000001916740300000000000000000000000000000000000000001790556108fa565b600254604051600160a060020a03909116903480156108fc02916000818181858888f193505050501580156108c3573d6000803e3d6000fd5b506002805474ff00000000000000000000000000000000000000001916740400000000000000000000000000000000000000001790555b565b6006545b90565b600054600160a060020a03163314610953576040805160e560020a62461bcd0281526020600482015260156024820152600080516020610a86833981519152604482015290519081900360640190fd5b66038d7ea4c68000341461096657600080fd5b600383905560018054600160a060020a0380851673ffffffffffffffffffffffffffffffffffffffff1992831617835560028054918516919092161780825574ff0000000000000000000000000000000000000000191674010000000000000000000000000000000000000000830217905550505050565b8015156109ea57600080fd5b50565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610a2e57805160ff1916838001178555610a5b565b82800160010185558215610a5b579182015b82811115610a5b578251825591602001919060010190610a40565b50610a67929150610a6b565b5090565b61090091905b80821115610a675760008155600101610a715600596f75277265206e6f7420746865206f776e65722e0000000000000000000000a165627a7a72305820d2878137460b5caeac2c65ddcd4dabda486dc527e1e0e8c23c8329222e7db2800029";

    public static final String FUNC_FINALIZEPOLICY = "finalizePolicy";

    public static final String FUNC_ADDTRACK = "addTrack";

    public static final String FUNC_VERIFYANDFINALIZE = "verifyAndFinalize";

    public static final String FUNC_LISTTOTALTRACKS = "listTotalTracks";

    public static final String FUNC_SETUP = "setup";
//
    public static final Event FINISHEDTRACKANDPAYED_EVENT = new Event("FinishedTrackAndPayed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}), new ArrayList<>());
//    ;
//
    public static final Event NEWTRACK_EVENT = new Event("NewTrack", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, 
            		new TypeReference<Uint256>() {}, 
            		new TypeReference<Utf8String>() {}, 
            		new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, 
            		new TypeReference<Utf8String>() {}, 
            		new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}), new ArrayList<>());
    ;
//
    public static final Event CHANGESTATUS_EVENT = new Event("ChangeStatus", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}), new ArrayList<>());
//    ;

    protected TrackingV1(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TrackingV1(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<TransactionReceipt> finalizePolicy() {
        final Function function = new Function(
                FUNC_FINALIZEPOLICY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> addTrack(BigInteger _idDeviceTrack, String _date, String _time, BigInteger _temp, String _humidity, String _latitude, String _longitute) {
        final Function function = new Function(
                FUNC_ADDTRACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_idDeviceTrack), 
                new org.web3j.abi.datatypes.Utf8String(_date), 
                new org.web3j.abi.datatypes.Utf8String(_time), 
                new org.web3j.abi.datatypes.generated.Uint256(_temp), 
                new org.web3j.abi.datatypes.Utf8String(_humidity), 
                new org.web3j.abi.datatypes.Utf8String(_latitude), 
                new org.web3j.abi.datatypes.Utf8String(_longitute)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> verifyAndFinalize(BigInteger weiValue) {
        final Function function = new Function(
                FUNC_VERIFYANDFINALIZE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> listTotalTracks() {
        final Function function = new Function(FUNC_LISTTOTALTRACKS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> setup(BigInteger _maxTempAccepted, String _sender, String _receiver, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SETUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_maxTempAccepted), 
                new org.web3j.abi.datatypes.Address(_sender), 
                new org.web3j.abi.datatypes.Address(_receiver)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public static RemoteCall<TrackingV1> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TrackingV1.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TrackingV1> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TrackingV1.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public List<FinishedTrackAndPayedEventResponse> getFinishedTrackAndPayedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FINISHEDTRACKANDPAYED_EVENT, transactionReceipt);
        ArrayList<FinishedTrackAndPayedEventResponse> responses = new ArrayList<FinishedTrackAndPayedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FinishedTrackAndPayedEventResponse typedResponse = new FinishedTrackAndPayedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse._address = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.eventValue = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<FinishedTrackAndPayedEventResponse> finishedTrackAndPayedEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, FinishedTrackAndPayedEventResponse>() {
            @Override
            public FinishedTrackAndPayedEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(FINISHEDTRACKANDPAYED_EVENT, log);
                FinishedTrackAndPayedEventResponse typedResponse = new FinishedTrackAndPayedEventResponse();
                typedResponse.log = log;
                typedResponse._address = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.eventValue = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<FinishedTrackAndPayedEventResponse> finishedTrackAndPayedEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FINISHEDTRACKANDPAYED_EVENT));
        return finishedTrackAndPayedEventObservable(filter);
    }

    public List<NewTrackEventResponse> getNewTrackEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWTRACK_EVENT, transactionReceipt);
        ArrayList<NewTrackEventResponse> responses = new ArrayList<NewTrackEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewTrackEventResponse typedResponse = new NewTrackEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse._idDeviceTrack = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse._date = (String) eventValues.getNonIndexedValues().get(2).getValue();
            typedResponse._time = (String) eventValues.getNonIndexedValues().get(3).getValue();
            typedResponse._temp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
            typedResponse._humidity = (String) eventValues.getNonIndexedValues().get(5).getValue();
            typedResponse._latitude = (String) eventValues.getNonIndexedValues().get(6).getValue();
            typedResponse._longitute = (String) eventValues.getNonIndexedValues().get(7).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<NewTrackEventResponse> newTrackEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, NewTrackEventResponse>() {
            @Override
            public NewTrackEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWTRACK_EVENT, log);
                NewTrackEventResponse typedResponse = new NewTrackEventResponse();
                typedResponse.log = log;
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse._idDeviceTrack = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse._date = (String) eventValues.getNonIndexedValues().get(2).getValue();
                typedResponse._time = (String) eventValues.getNonIndexedValues().get(3).getValue();
                typedResponse._temp = (BigInteger) eventValues.getNonIndexedValues().get(4).getValue();
                typedResponse._humidity = (String) eventValues.getNonIndexedValues().get(5).getValue();
                typedResponse._latitude = (String) eventValues.getNonIndexedValues().get(6).getValue();
                typedResponse._longitute = (String) eventValues.getNonIndexedValues().get(7).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<NewTrackEventResponse> newTrackEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWTRACK_EVENT));
        return newTrackEventObservable(filter);
    }

    public List<ChangeStatusEventResponse> getChangeStatusEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(CHANGESTATUS_EVENT, transactionReceipt);
        ArrayList<ChangeStatusEventResponse> responses = new ArrayList<ChangeStatusEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ChangeStatusEventResponse typedResponse = new ChangeStatusEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ChangeStatusEventResponse> changeStatusEventObservable(EthFilter filter) {
        return web3j.ethLogObservable(filter).map(new Func1<Log, ChangeStatusEventResponse>() {
            @Override
            public ChangeStatusEventResponse call(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(CHANGESTATUS_EVENT, log);
                ChangeStatusEventResponse typedResponse = new ChangeStatusEventResponse();
                typedResponse.log = log;
                typedResponse.status = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Observable<ChangeStatusEventResponse> changeStatusEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(CHANGESTATUS_EVENT));
        return changeStatusEventObservable(filter);
    }

    public static TrackingV1 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TrackingV1(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static TrackingV1 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TrackingV1(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class FinishedTrackAndPayedEventResponse {
        public Log log;

        public String _address;

        public BigInteger eventValue;

        public BigInteger status;
    }

    public static class NewTrackEventResponse {
        public Log log;

        public BigInteger status;

        public BigInteger _idDeviceTrack;

        public String _date;

        public String _time;

        public BigInteger _temp;

        public String _humidity;

        public String _latitude;

        public String _longitute;
    }

    public static class ChangeStatusEventResponse {
        public Log log;

        public BigInteger status;
    }
}
