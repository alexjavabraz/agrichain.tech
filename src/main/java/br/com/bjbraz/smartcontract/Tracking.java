package br.com.bjbraz.smartcontract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
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
public class Tracking extends Contract {
    private static final String BINARY = "608060405234801561001057600080fd5b5060008054600160a060020a031916331781556002805460a060020a60ff0219169055611c1e90819061004390396000f3006080604052600436106100985763ffffffff7c01000000000000000000000000000000000000000000000000000000006000350416630d2ee91b811461009d578063455312ad146100c85780634f0d49d5146100df57806350ffdf7c146100f2578063535975e21461011457806380151fa314610129578063ab03085c1461013e578063f48ed22d1461015e578063fac2977514610173575b600080fd5b3480156100a957600080fd5b506100b2610195565b6040516100bf9190611a1c565b60405180910390f35b3480156100d457600080fd5b506100dd610631565b005b6100dd6100ed36600461189e565b6106e5565b3480156100fe57600080fd5b50610107610793565b6040516100bf9190611b0f565b34801561012057600080fd5b506100dd61079a565b34801561013557600080fd5b506101076108a7565b34801561014a57600080fd5b50610107610159366004611773565b6108ad565b34801561016a57600080fd5b506100b2611051565b34801561017f57600080fd5b50610188611493565b6040516100bf9190611a2d565b6000546060908190600160a060020a031633146101d05760405160e560020a62461bcd0281526004016101c790611aef565b60405180910390fd5b604080516008808252610120820190925290816020015b60608152602001906001900390816101e75750506012805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152939450908301828280156102835780601f1061025857610100808354040283529160200191610283565b820191906000526020600020905b81548152906001019060200180831161026657829003601f168201915b505050505081600081518110151561029757fe5b602090810290910101526013546102ad9061149c565b8160018151811015156102bc57fe5b60209081029091018101919091526014805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529283018282801561034e5780601f106103235761010080835404028352916020019161034e565b820191906000526020600020905b81548152906001019060200180831161033157829003601f168201915b505050505081600281518110151561036257fe5b60209081029091018101919091526015805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156103f45780601f106103c9576101008083540402835291602001916103f4565b820191906000526020600020905b8154815290600101906020018083116103d757829003601f168201915b505050505081600381518110151561040857fe5b6020908102909101015260165461041e9061149c565b81600481518110151561042d57fe5b60209081029091018101919091526017805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156104bf5780601f10610494576101008083540402835291602001916104bf565b820191906000526020600020905b8154815290600101906020018083116104a257829003601f168201915b50505050508160058151811015156104d357fe5b60209081029091018101919091526018805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156105655780601f1061053a57610100808354040283529160200191610565565b820191906000526020600020905b81548152906001019060200180831161054857829003601f168201915b505050505081600681518110151561057957fe5b60209081029091018101919091526019805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529283018282801561060b5780601f106105e05761010080835404028352916020019161060b565b820191906000526020600020905b8154815290600101906020018083116105ee57829003601f168201915b505050505081600781518110151561061f57fe5b602090810290910101529050805b5090565b600054600160a060020a0316331461065e5760405160e560020a62461bcd0281526004016101c790611aef565b6002805474ff000000000000000000000000000000000000000019167402000000000000000000000000000000000000000017908190556040517f9146df6dc3725d43aefb4c714955239b068466fd5c10ae1abf2ec3453316839e916106cf9160a060020a90910460ff1690611a41565b60405180910390a1600054600160a060020a0316ff5b600054600160a060020a031633146107125760405160e560020a62461bcd0281526004016101c790611aef565b66038d7ea4c6800034101561072657600080fd5b6003849055600683905560018054600160a060020a0380851673ffffffffffffffffffffffffffffffffffffffff1992831617835560028054918516919092161780825574ff0000000000000000000000000000000000000000191660a060020a83021790555050505050565b6004545b90565b60008054600160a060020a031633146107c85760405160e560020a62461bcd0281526004016101c790611aef565b60055460ff161580156107de575060085460ff16155b1561082a57506001546002805474ff0000000000000000000000000000000000000000191674030000000000000000000000000000000000000000179055600160a060020a031661086d565b506000546002805474ff0000000000000000000000000000000000000000191674040000000000000000000000000000000000000000179055600160a060020a03165b604051600160a060020a03821690303180156108fc02916000818181858888f193505050501580156108a3573d6000803e3d6000fd5b5050565b60095490565b60008054819081908190600160a060020a031633146108e15760405160e560020a62461bcd0281526004016101c790611aef565b6002805460a060020a900460ff1660058111156108fa57fe5b141561091b5760405160e560020a62461bcd0281526004016101c790611aff565b600360025460a060020a900460ff16600581111561093557fe5b14156109565760405160e560020a62461bcd0281526004016101c790611aff565b600460025460a060020a900460ff16600581111561097057fe5b14156109915760405160e560020a62461bcd0281526004016101c790611aff565b61099a8b6115c3565b6003548810159250600654881115915060045488116109bb576004546109bd565b875b60045560075488106109d1576007546109d3565b875b60075560408051610100810182528c8152426020808301919091529181018c9052606081018b9052608081018a905260a0810189905260c0810188905260e08101879052600980546001810180835560009290925282518051929460089092027f6e1540171b6c0c960b71a7020d9f60077f6af931a8bbf590da0223dacf75c7af0192610a6392849201906115f7565b50602082810151600183015560408301518051610a8692600285019201906115f7565b5060608201518051610aa29160038401916020909101906115f7565b506080820151600482015560a08201518051610ac89160058401916020909101906115f7565b5060c08201518051610ae49160068401916020909101906115f7565b5060e08201518051610b009160078401916020909101906115f7565b5050509050828015610b15575060055460ff16155b15610d7b576005805460ff19168415151790556009805482908110610b3657fe5b9060005260206000209060080201600a60008201816000019080546001816001161561010002031660029004610b6d929190611671565b506001820154816001015560028201816002019080546001816001161561010002031660029004610b9f929190611671565b5060038201816003019080546001816001161561010002031660029004610bc7929190611671565b506004820154816004015560058201816005019080546001816001161561010002031660029004610bf9929190611671565b5060068201816006019080546001816001161561010002031660029004610c21929190611671565b5060078201816007019080546001816001161561010002031660029004610c49929190611671565b5050600c805460408051602060026000196001861615610100020190941693909304601f81018490048402820184019092528181528594503393610ce69391929091830182828015610cdc5780601f10610cb157610100808354040283529160200191610cdc565b820191906000526020600020905b815481529060010190602001808311610cbf57829003601f168201915b50505050506115d3565b600a8054604080516020601f60026101006001871615026000190190951694909404938401819004810282018101909252828152610d489390929091830182828015610cdc5780601f10610cb157610100808354040283529160200191610cdc565b604080517f4e6577206d61782076616c756520726561636865640000000000000000000000815290519081900360150190a45b818015610d8b575060085460ff16155b15610fbc576008805460ff19168315151790556009805482908110610dac57fe5b9060005260206000209060080201601260008201816000019080546001816001161561010002031660029004610de3929190611671565b506001820154816001015560028201816002019080546001816001161561010002031660029004610e15929190611671565b5060038201816003019080546001816001161561010002031660029004610e3d929190611671565b506004820154816004015560058201816005019080546001816001161561010002031660029004610e6f929190611671565b5060068201816006019080546001816001161561010002031660029004610e97929190611671565b5060078201816007019080546001816001161561010002031660029004610ebf929190611671565b5050600c805460408051602060026000196001861615610100020190941693909304601f81018490048402820184019092528181528594503393610f279391929091830182828015610cdc5780601f10610cb157610100808354040283529160200191610cdc565b600a8054604080516020601f60026101006001871615026000190190951694909404938401819004810282018101909252828152610f899390929091830182828015610cdc5780601f10610cb157610100808354040283529160200191610cdc565b604080517f4e6577206d696e2076616c756520726561636865640000000000000000000000815290519081900360150190a45b6002805474ff000000000000000000000000000000000000000019167405000000000000000000000000000000000000000017908190556040517fd4f2c5d5177870d3df6e34f8c11a95092f97d4afb3269f3b83c9c98830785f849161103b9160a060020a90910460ff16908e908e908e908e908e908e908e90611a4f565b60405180910390a19a9950505050505050505050565b6000546060908190600160a060020a031633146110835760405160e560020a62461bcd0281526004016101c790611aef565b604080516008808252610120820190925290816020015b606081526020019060019003908161109a575050600a805460408051602060026001851615610100026000190190941693909304601f8101849004840282018401909252818152939450908301828280156111365780601f1061110b57610100808354040283529160200191611136565b820191906000526020600020905b81548152906001019060200180831161111957829003601f168201915b505050505081600081518110151561114a57fe5b60209081029091010152600b546111609061149c565b81600181518110151561116f57fe5b6020908102909101810191909152600c805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156112015780601f106111d657610100808354040283529160200191611201565b820191906000526020600020905b8154815290600101906020018083116111e457829003601f168201915b505050505081600281518110151561121557fe5b6020908102909101810191909152600d805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156112a75780601f1061127c576101008083540402835291602001916112a7565b820191906000526020600020905b81548152906001019060200180831161128a57829003601f168201915b50505050508160038151811015156112bb57fe5b60209081029091010152600e546112d19061149c565b8160048151811015156112e057fe5b6020908102909101810191909152600f805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156113725780601f1061134757610100808354040283529160200191611372565b820191906000526020600020905b81548152906001019060200180831161135557829003601f168201915b505050505081600581518110151561138657fe5b60209081029091018101919091526010805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156114185780601f106113ed57610100808354040283529160200191611418565b820191906000526020600020905b8154815290600101906020018083116113fb57829003601f168201915b505050505081600681518110151561142c57fe5b60209081029091018101919091526011805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529283018282801561060b5780601f106105e05761010080835404028352916020019161060b565b60055460ff1690565b606060008082818515156114e55760408051808201909152600181527f3000000000000000000000000000000000000000000000000000000000000000602082015294506115ba565b8593505b831561150057600190920191600a840493506114e9565b826040519080825280601f01601f19166020018201604052801561152e578160200160208202803883390190505b5091505060001982015b85156115b65781516000198201917f01000000000000000000000000000000000000000000000000000000000000006030600a8a06010291849190811061157b57fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350600a86049550611538565b8194505b50505050919050565b805181906000106108a357600080fd5b8051600090829015156115e957600091506115f1565b602083015191505b50919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061163857805160ff1916838001178555611665565b82800160010185558215611665579182015b8281111561166557825182559160200191906001019061164a565b5061062d9291506116e6565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106116aa5780548555611665565b8280016001018555821561166557600052602060002091601f016020900482015b828111156116655782548255916001019190600101906116cb565b61079791905b8082111561062d57600081556001016116ec565b600061170c8235611b87565b9392505050565b6000601f8201831361172457600080fd5b813561173761173282611b44565b611b1d565b9150808252602083016020830185838301111561175357600080fd5b61175e838284611b9e565b50505092915050565b600061170c8235610797565b600080600080600080600060e0888a03121561178e57600080fd5b873567ffffffffffffffff8111156117a557600080fd5b6117b18a828b01611713565b975050602088013567ffffffffffffffff8111156117ce57600080fd5b6117da8a828b01611713565b965050604088013567ffffffffffffffff8111156117f757600080fd5b6118038a828b01611713565b95505060606118148a828b01611767565b945050608088013567ffffffffffffffff81111561183157600080fd5b61183d8a828b01611713565b93505060a088013567ffffffffffffffff81111561185a57600080fd5b6118668a828b01611713565b92505060c088013567ffffffffffffffff81111561188357600080fd5b61188f8a828b01611713565b91505092959891949750929550565b600080600080608085870312156118b457600080fd5b60006118c08787611767565b94505060206118d187828801611767565b93505060406118e287828801611700565b92505060606118f387828801611700565b91505092959194509250565b600061190a82611b72565b8084526020840193508360208202850161192385611b6c565b60005b8481101561195a57838303885261193e83835161197e565b925061194982611b6c565b602098909801979150600101611926565b50909695505050505050565b61196f81611b76565b82525050565b61196f81611b93565b600061198982611b72565b80845261199d816020860160208601611baa565b6119a681611bda565b9093016020019392505050565b601581527f596f75277265206e6f7420746865206f776e65722e0000000000000000000000602082015260400190565b600f81527f496e76616c6964205354415455532e0000000000000000000000000000000000602082015260400190565b61196f81610797565b6020808252810161170c81846118ff565b60208101611a3b8284611966565b92915050565b60208101611a3b8284611975565b6101008101611a5e828b611975565b8181036020830152611a70818a61197e565b90508181036040830152611a84818961197e565b90508181036060830152611a98818861197e565b9050611aa76080830187611a13565b81810360a0830152611ab9818661197e565b905081810360c0830152611acd818561197e565b905081810360e0830152611ae1818461197e565b9a9950505050505050505050565b60208082528101611a3b816119b3565b60208082528101611a3b816119e3565b60208101611a3b8284611a13565b60405181810167ffffffffffffffff81118282101715611b3c57600080fd5b604052919050565b600067ffffffffffffffff821115611b5b57600080fd5b506020601f91909101601f19160190565b60200190565b5190565b151590565b60006006821061062d57fe5b600160a060020a031690565b6000611a3b82611b7b565b82818337506000910152565b60005b83811015611bc5578181015183820152602001611bad565b83811115611bd4576000848401525b50505050565b601f01601f1916905600a265627a7a72305820181a9edc8484ed7146bbd7c5f40dd7c98ed714fcbbf42ffa30e836e57ff5a40c6c6578706572696d656e74616cf50037";

    public static final String FUNC_GETMINTRACKED = "getMinTracked";

    public static final String FUNC_FINALIZEPOLICY = "finalizePolicy";

    public static final String FUNC_SETUP = "setup";

    public static final String FUNC_GETMAXTEMPERATURERECEIVED = "getMaxTemperatureReceived";

    public static final String FUNC_VERIFYANDFINALIZE = "verifyAndFinalize";

    public static final String FUNC_LISTTOTALTRACKS = "listTotalTracks";

    public static final String FUNC_ADDTRACK = "addTrack";

    public static final String FUNC_GETMAXTRACKED = "getMaxTracked";

    public static final String FUNC_GETMAXTEMPERATUREREACHED = "getMaxTemperatureReached";

    public static final Event FINISHEDTRACKANDPAYED_EVENT = new Event("FinishedTrackAndPayed", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint8>() {}), new ArrayList<>());
    ;

    public static final Event NEWTRACK_EVENT = new Event("NewTrack", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}, new TypeReference<Utf8String>() {}, 
            		new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, 
            		new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}), new ArrayList<>());
    ;

    public static final Event CHANGESTATUS_EVENT = new Event("ChangeStatus", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}), new ArrayList<>());
    ;

    protected Tracking(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Tracking(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public RemoteCall<List> getMinTracked() {
        final Function function = new Function(FUNC_GETMINTRACKED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<TransactionReceipt> finalizePolicy() {
        final Function function = new Function(
                FUNC_FINALIZEPOLICY, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> setup(BigInteger _maxTempAccepted, BigInteger _minTemperatureAccepted, String _sender, String _receiver, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_SETUP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_maxTempAccepted), 
                new org.web3j.abi.datatypes.generated.Uint256(_minTemperatureAccepted), 
                new org.web3j.abi.datatypes.Address(_sender), 
                new org.web3j.abi.datatypes.Address(_receiver)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<BigInteger> getMaxTemperatureReceived() {
        final Function function = new Function(FUNC_GETMAXTEMPERATURERECEIVED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> verifyAndFinalize() {
        final Function function = new Function(
                FUNC_VERIFYANDFINALIZE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> listTotalTracks() {
        final Function function = new Function(FUNC_LISTTOTALTRACKS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> addTrack(String _idDeviceTrack, String _date, String _time, BigInteger _temp, String _humidity, String _latitude, String _longitute) {
        final Function function = new Function(
                FUNC_ADDTRACK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(_idDeviceTrack), 
                new org.web3j.abi.datatypes.Utf8String(_date), 
                new org.web3j.abi.datatypes.Utf8String(_time), 
                new org.web3j.abi.datatypes.generated.Uint256(_temp), 
                new org.web3j.abi.datatypes.Utf8String(_humidity), 
                new org.web3j.abi.datatypes.Utf8String(_latitude), 
                new org.web3j.abi.datatypes.Utf8String(_longitute)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<List> getMaxTracked() {
        final Function function = new Function(FUNC_GETMAXTRACKED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteCall<List>(
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteCall<Boolean> getMaxTemperatureReached() {
        final Function function = new Function(FUNC_GETMAXTEMPERATUREREACHED, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public static RemoteCall<Tracking> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Tracking.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Tracking> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Tracking.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
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
            typedResponse._idDeviceTrack = (String) eventValues.getNonIndexedValues().get(1).getValue();
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
                typedResponse._idDeviceTrack = (String) eventValues.getNonIndexedValues().get(1).getValue();
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

    public static Tracking load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Tracking(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static Tracking load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Tracking(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
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

        public String _idDeviceTrack;

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
